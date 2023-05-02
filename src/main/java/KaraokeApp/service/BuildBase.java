/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.service;

import KaraokeApp.model.Conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author 84374
 */
public class BuildBase {

    public BuildBase() {
        try {
            String targer = Conn.getInstance().url;
            String[] part = targer.split(";");
            part[1] = "databaseName=master";
            String newTaget = part[0] + ";" + part[1] + ";" + part[2] + ";" + part[3] + ";" + part[4];
            System.out.println(newTaget);
            Connection conn = DriverManager.getConnection(newTaget);
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE [KARAOKEDATA]\n"
                    + "CREATE TABLE [KARAOKEDATA].[CUSTOMER](\n"
                    + "	[NUMORDER] [int] IDENTITY(1,1) NOT NULL,\n"
                    + "	[FULLNAME] [varchar](100) NULL,\n"
                    + "	[IDBILL]  AS ('HD'+CONVERT([varchar](30),[NUMORDER])) PERSISTED NOT NULL,\n"
                    + "	[DOB] [varchar](20) NULL,\n"
                    + "	[GENDER] [varchar](20) NULL,\n"
                    + "	[TYPEROOM] [varchar](50) NULL,\n"
                    + "	[ROOMNO] [varchar](50) NULL,\n"
                    + "	[HOURIN] [varchar](5) NULL,\n"
                    + "	[MINUTEIN] [varchar](5) NULL,\n"
                    + "	[HOUROUT] [varchar](5) NULL,\n"
                    + "	[MINUTEOUT] [varchar](5) NULL,\n"
                    + "	[DATEIN] [varchar](20) NULL,\n"
                    + "	[MONEYTOOK] [varchar](20) NULL,\n"
                    + "PRIMARY KEY CLUSTERED \n"
                    + "(\n"
                    + "	[IDBILL] ASC\n"
                    + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]\n"
                    + ") ON [PRIMARY]\n"
                    + "GO\n"
                    + "/****** Object:  Table [dbo].[DISHOFFOOD]    Script Date: 04/24/2023 12:14:17 AM ******/\n"
                    + "SET ANSI_NULLS ON\n"
                    + "GO\n"
                    + "SET QUOTED_IDENTIFIER ON\n"
                    + "GO\n"
                    + "CREATE TABLE [KARAOKEDATA].[DISHOFFOOD](\n"
                    + "	[NUMORDER] [int] IDENTITY(1,1) NOT NULL,\n"
                    + "	[IDFOOD]  AS ('F'+CONVERT([varchar](30),[NUMORDER])) PERSISTED NOT NULL,\n"
                    + "	[NAMEOFDISH] [varchar](50) NULL,\n"
                    + "	[UNIT] [varchar](20) NULL,\n"
                    + "	[UNITPRICE] [varchar](20) NULL,\n"
                    + "PRIMARY KEY CLUSTERED \n"
                    + "(\n"
                    + "	[IDFOOD] ASC\n"
                    + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]\n"
                    + ") ON [PRIMARY]\n"
                    + "GO\n"
                    + "/****** Object:  Table [dbo].[FOODCONSUMED]    Script Date: 04/24/2023 12:14:17 AM ******/\n"
                    + "SET ANSI_NULLS ON\n"
                    + "GO\n"
                    + "SET QUOTED_IDENTIFIER ON\n"
                    + "GO\n"
                    + "CREATE TABLE [KARAOKEDATA].[FOODCONSUMED](\n"
                    + "	[NUMORDER] [int] IDENTITY(1,1) NOT NULL,\n"
                    + "	[IDBILL] [varchar](32) NULL,\n"
                    + "	[IDFOOD] [varchar](31) NULL,\n"
                    + "	[AMOUNT] [varchar](10) NULL\n"
                    + ") ON [PRIMARY]\n"
                    + "GO\n"
                    + "/****** Object:  Table [dbo].[ROOMNO]    Script Date: 04/24/2023 12:14:17 AM ******/\n"
                    + "SET ANSI_NULLS ON\n"
                    + "GO\n"
                    + "SET QUOTED_IDENTIFIER ON\n"
                    + "GO\n"
                    + "CREATE TABLE [KARAOKEDATA].[ROOMNO](\n"
                    + "	[NUMORDER] [int] IDENTITY(1,1) NOT NULL,\n"
                    + "	[ROOMNO] [varchar](50) NOT NULL,\n"
                    + "	[TYPEROOM] [varchar](50) NULL,\n"
                    + "PRIMARY KEY CLUSTERED \n"
                    + "(\n"
                    + "	[ROOMNO] ASC\n"
                    + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]\n"
                    + ") ON [PRIMARY]\n"
                    + "GO\n"
                    + "/****** Object:  Table [dbo].[TYPEROOM]    Script Date: 04/24/2023 12:14:17 AM ******/\n"
                    + "SET ANSI_NULLS ON\n"
                    + "GO\n"
                    + "SET QUOTED_IDENTIFIER ON\n"
                    + "GO\n"
                    + "CREATE TABLE [KARAOKEDATA].[TYPEROOM](\n"
                    + "	[NAMETYPE] [varchar](50) NOT NULL,\n"
                    + "	[PRICEONHOUR] [varchar](20) NULL,\n"
                    + "PRIMARY KEY CLUSTERED \n"
                    + "(\n"
                    + "	[NAMETYPE] ASC\n"
                    + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]\n"
                    + ") ON [PRIMARY]\n"
                    + "GO\n"
                    + "ALTER TABLE [KARAOKEDATA].[FOODCONSUMED]  WITH CHECK ADD FOREIGN KEY([IDBILL])\n"
                    + "REFERENCES [KARAOKEDATA].[CUSTOMER] ([IDBILL])\n"
                    + "GO\n"
                    + "ALTER TABLE [KARAOKEDATA].[FOODCONSUMED]  WITH CHECK ADD FOREIGN KEY([IDFOOD])\n"
                    + "REFERENCES [KARAOKEDATA].[DISHOFFOOD] ([IDFOOD])\n"
                    + "GO\n"
                    + "ALTER TABLE [KARAOKEDATA].[ROOMNO]  WITH CHECK ADD FOREIGN KEY([TYPEROOM])\n"
                    + "REFERENCES [KARAOKEDATA].[TYPEROOM] ([NAMETYPE])\n"
                    + "GO\n"
                    + "ALTER DATABASE [KARAOKEDATA] SET  READ_WRITE \n"
                    + "GO\n"
                    + "INSERT INTO KARAOKEDATA.TYPEROOM\n"
                    + "VALUES	\n"
                    + "('PHONG VIP','1000000.0'),\n"
                    + "('PHONG TRUNG','700000.0'),\n"
                    + "('PHONG THUONG','500000.0');\n"
                    + "GO\n"
                    + "INSERT INTO KARAOKEDATA.DISHOFFOOD\n"
                    + "VALUES\n"
                    + "('Snack','Pack','20000.0'),\n"
                    + "('Fruit','Dish','40000.0'),\n"
                    + "('Beer','Bottle','30000.0'),\n"
                    + "('Coca','Bottle','25000.0');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            System.out.println("Build dataBase success !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("something wrong when build database");
        }
    }
}
