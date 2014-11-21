USE [ots]
GO

/****** Object:  StoredProcedure [dbo].[spOilTransaction]    Script Date: 11/21/2014 1:17:21 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--Procedure to execute or abort oil transaction based on validations
CREATE PROCEDURE [dbo].[spOilTransaction]
@cid int,
@trxType bit, --0 = sell and 1 = buy
@quantity float, 
@commtype int, --1 = oil and 2 = cash
@currentOilPrice float, --current oil price
@userid int --if trader cid!=userid
AS
BEGIN
DECLARE @totalTrxQuantity float,
--@oilBalance float,
@commissionRate float,
@trxAmount float,
@feeAmount float,
@feeInOil  float,
@totaltrxamount float
--SET @oilBalance =(SELECT oilbalance FROM CLIENT WHERE cid = @cid) --check the oil balance in client's account
--IF @oilBalance<@quantity THEN exit
--we will check the above condition using ajax call in the front end at the time user enters the qunatity in textbox

--calculate the monthly transaction quantity of the client
SET @totalTrxQuantity =(select sum(quantity) from dbo.oiltransaction
where cid =@cid
and 
datename(MONTH,trxtime)=datename(month,GETDATE()))

--based on the transaction quantity compute the level of the client
IF @totalTrxQuantity>30 
  begin
	SELECT @commissionRate = commissionrate from LEVEL where lid=1 --lid =1 means Gold
	UPDATE CLIENT SET lid=1 WHERE cid=@cid
  end
ELSE
begin
	SELECT @commissionRate = commissionrate from LEVEL where lid=2 --lid =2 means Silver
	UPDATE CLIENT SET lid=2 WHERE cid=@cid --its required when the month changes and in the previous month client was gold
	end
--compute commission fee

IF (@trxType =1 AND @commtype=2) --buying and paying commission fee in cash
	begin
      UPDATE CLIENT SET oilbalance = oilbalance + @quantity where cid = @cid
	  SET @trxAmount = @quantity * @currentOilPrice
	  SET @feeAmount = 0.01 * @commissionRate * @quantity * @currentOilPrice
	  SET @feeInOil = 0
	  SET @totaltrxamount=@trxAmount+@feeAmount
	end

ELSE IF (@trxType =1 AND @commtype=1) --buying and paying commission fee in oil
	begin
      UPDATE CLIENT SET oilbalance = oilbalance + @quantity where cid = @cid
	  SET @trxAmount = @quantity * @currentOilPrice
	  SET @feeAmount = 0.01 * @commissionRate * @quantity * @currentOilPrice
	  SET @feeInOil = @feeAmount/@currentOilPrice-- earlier it was set to @feeAmount
	  SET @totaltrxamount=@trxAmount --as he is paying fee in oil
	  UPDATE CLIENT SET oilbalance = oilbalance - @feeInOil 
	end

ELSE IF (@trxType =0 AND @commtype=2) --selling and paying commission fee in cash
	begin
      UPDATE CLIENT SET oilbalance = oilbalance - @quantity where cid = @cid
	  SET @feeAmount = 0.01 * @commissionRate * @quantity * @currentOilPrice
	  SET @feeInOil = 0
	  SET @trxAmount=0
	  SET @totaltrxamount=@feeAmount
	end
ELSE --selling and paying commission fee in oil
	begin
      UPDATE CLIENT SET oilbalance = oilbalance - @quantity where cid = @cid
	  SET @feeAmount = 0.01 * @commissionRate * @quantity * @currentOilPrice
	  SET @feeInOil = @feeAmount/@currentOilPrice
	  SET @trxAmount=0
	  SET @totaltrxamount=0
	end

INSERT INTO [dbo].[OILTRANSACTION]
           ([cid]
           ,[quantity]
           ,[trxtype]
           ,[trxamount]
           ,[feeamount]
           ,[feeinoil]
           ,[commtype]
           ,[isCancelled]
		   ,[totaltrxamount]
		   ,[trxbalance]
           ,[uid])
     VALUES
           (@cid
           ,@quantity
		   ,@trxType
		   ,@trxAmount
           ,@feeAmount
           ,@feeInOil
           ,@commtype
           ,1 --successful transaction
		   ,@totaltrxamount
		   ,@totaltrxamount
           ,@userid
           )

END

GO


