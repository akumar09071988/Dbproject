USE [ots]
GO

/****** Object:  StoredProcedure [dbo].[spTransactionCancellation]    Script Date: 11/21/2014 1:18:14 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--Procedure to cancel transaction
CREATE PROCEDURE [dbo].[spTransactionCancellation]
@trxid int

AS
BEGIN
DECLARE @paymentamount float,
@quantity float,
@cid int,
@feeinoil float,
@trxType bit,
@commtype int,
@totalTrxQuantity float

SET @trxType=(select trxType from dbo.OILTRANSACTION where trxid =@trxid)
SET @commtype=(select commtype from dbo.OILTRANSACTION where trxid =@trxid) 

IF (@trxType =1 AND @commtype=2) --buying and paying commission fee in cash
	begin
		SET @paymentamount =(select sum(paymentamt) from dbo.payment where trxid =@trxid)
		SET @quantity=(select quantity from dbo.OILTRANSACTION where trxid =@trxid)
		SET @cid = (select cid from dbo.OILTRANSACTION where trxid =@trxid)
		SET @totalTrxQuantity =(select sum(quantity) from dbo.oiltransaction
		where cid =@cid and datename(MONTH,trxtime)=datename(month,GETDATE()))
		UPDATE CLIENT SET oilbalance = oilbalance - @quantity where cid = @cid
		UPDATE OILTRANSACTION SET trxbalance=trxbalance+@paymentamount,isCancelled=0 where trxid=@trxid 
		--display the trxbalance to the user as the refund amount on the screen (for all the cases)
	end

ELSE IF (@trxType =1 AND @commtype=1) --buying and paying commission fee in oil
	begin
		SET @paymentamount =(select sum(paymentamt) from dbo.payment where trxid =@trxid)
		SET @quantity=(select quantity from dbo.OILTRANSACTION where trxid =@trxid)
		SET @cid = (select cid from dbo.OILTRANSACTION where trxid =@trxid)
		SET @feeinoil=(select feeinoil from dbo.OILTRANSACTION where trxid =@trxid)
		UPDATE CLIENT SET oilbalance = oilbalance - @quantity + @feeinoil where cid = @cid
		UPDATE OILTRANSACTION SET trxbalance=trxbalance+@paymentamount,isCancelled=0 where trxid=@trxid 
	end

ELSE IF (@trxType =0 AND @commtype=2) --selling and paying commission fee in cash
	begin
		SET @paymentamount =(select sum(paymentamt) from dbo.payment where trxid =@trxid)
		SET @quantity=(select quantity from dbo.OILTRANSACTION where trxid =@trxid)
		SET @cid = (select cid from dbo.OILTRANSACTION where trxid =@trxid)
		UPDATE CLIENT SET oilbalance = oilbalance + @quantity where cid = @cid
		UPDATE OILTRANSACTION SET trxbalance=trxbalance+@paymentamount,isCancelled=0 where trxid=@trxid 
	end

ELSE --selling and paying commission fee in oil
	begin
        SET @quantity=(select quantity from dbo.OILTRANSACTION where trxid =@trxid)
		SET @cid = (select cid from dbo.OILTRANSACTION where trxid =@trxid)
		SET @feeinoil=(select feeinoil from dbo.OILTRANSACTION where trxid =@trxid)
		UPDATE CLIENT SET oilbalance = oilbalance - @quantity + @feeinoil where cid = @cid
		UPDATE OILTRANSACTION SET isCancelled=0 where trxid=@trxid
	end
	UPDATE PAYMENT SET isCancelled=1 where trxid=@trxid 
	IF @totalTrxQuantity>30 
			UPDATE CLIENT SET lid=1 WHERE cid=@cid
		ELSE
			UPDATE CLIENT SET lid=2 WHERE cid=@cid
END

GO


