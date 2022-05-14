package com.sapient.endur.dao;
/*
 * For  DDL(create/drop/alter) and static queries: Statement interface
 * For  DML(insert/update/delete) and dynamic queries: PreparedStatement
 * For  calling sub-programs of MySQL: CallableStatement
 */
public interface AirTicketInfoQueryMapper {
	public static final String ADD_AIRTICKETINFO= 
					"insert into AirTicketInfo(ticketid,profileid,flightid,date,status) values(?,?,?,?,?,?,?)";
	public static final String UPDATE_AIRTICKETINFO =
					"update employee set ticketId=?,profileId=?,flightId=?,date=?,status=?,where =ticketId?";
	
	public static final String DELETE_AIRTICKETINFO= "delete from AirTicketInfo where ticketId=?";
	public static final String GET_AIRTICKETINFO_BY_ID= "select * from AirTicketInfo where ticketId=?";
	public static final String GET_ALL_AIRTICKETINFO= "select * from AirTicketInfo";
	public static final String GET_AIRTICKETINFO_ID = "select max(ticketid) AirTicketInfo";
}