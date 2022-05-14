package com.sapient.endur.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.endur.model.AirTicketInfo;

public interface AirTicketInfoDAO {
	public abstract String addAirTicketInfo(AirTicketInfo airTicketInfo) throws SQLException;
	public abstract String deleteAirTicketInfo(String ticketId) throws SQLException;
	public abstract String updateAirTicketInfo(AirTicketInfo airTicketInfo) throws SQLException;
	public abstract AirTicketInfo getAirTicketInfoByTicketId(String ticketId) throws SQLException;
	public abstract List<AirTicketInfo> getAllAirTicketInfoByTicketId() throws SQLException;
	
	public abstract String getLatestAirTicket() throws SQLException;
}