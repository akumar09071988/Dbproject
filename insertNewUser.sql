USE [ots]
GO

/****** Object:  StoredProcedure [dbo].[insertNewUser]    Script Date: 11/21/2014 1:15:27 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[insertNewUser] 
	-- Add the parameters for the stored procedure here
	@userFirstName varchar(50),
	@userLastName varchar(50),
	@password varchar(50),
	@userType int,
	@phoneNum varchar(20),
	@cellPhoneNum varchar(20),
	@email varchar(25)
AS
BEGIN
	Declare
	   @cid int
	SET NOCOUNT ON;
	insert into dbo.USERS values(@userFirstName+' '+@userLastName,@password,@userType);
	 
	iF @userType=1
	 Begin
	  set @cid = SCOPE_IDENTITY();
	  insert into dbo.CLIENT values (@cid,@userFirstName,@userLastName,@phoneNum,@cellPhoneNum,@email,@password,-1,2);
     end
	
END

GO


