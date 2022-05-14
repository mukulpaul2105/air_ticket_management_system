package com.sapient.endur.service;

import java.sql.SQLException;
import java.util.List;

import com.sapient.endur.model.AirTicketInfo;

public interface AirTicketInfoService {

	public abstract String addAirTicketInfo(AirTicketInfo airTicketInfo) throws AirTicketInfoException;
	public abstract String deleteAirTicketInfo(String ticketId) throws AirTicketInfoException;
	public abstract String updateAirTicketInfo(AirTicketInfo airTicketInfo) throws AirTicketInfoException;
	public abstract AirTicketInfo getAirTicketInfoByTicketId(String ticketId) throws AirTicketInfoException;
	public abstract List<AirTicketInfo> getAllAirTicketInfoByTicketId() throws AirTicketInfoException;
	
	public abstract String getLatestAirTicket() throws AirTicketInfoException;
}