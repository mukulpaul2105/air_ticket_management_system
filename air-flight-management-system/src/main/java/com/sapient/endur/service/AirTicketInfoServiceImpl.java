package com.sapient.endur.service;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sapient.endur.dao.AirTicketInfoDAO;
import com.sapient.endur.dao.AirTicketInfoDaoImpl;
import com.sapient.endur.model.AirTicketInfo;

public class AirTicketInfoServiceImpl implements AirTicketInfoService{

	private  AirTicketInfoDAO airTicketInfoDao= new AirTicketInfoDaoImpl();
	private Logger logger= Logger.getLogger( AirTicketInfoServiceImpl.class);
	private Validator airTicketInfoValidator= new Validator();

	private boolean validateAirTicketInfo(AirTicketInfo airTicketInfo ) {
		if( airTicketInfoValidator.isValidTicketId(airTicketInfo.getTicketId())) {
			if( airTicketInfoValidator.isValidProfileid(airTicketInfo.getProfileid())) {
				if( airTicketInfoValidator.isValidFlightId(airTicketInfo.getFlightid())) {
					if( airTicketInfoValidator.isValidFlightDepatureDate(airTicketInfo.getFlightDepatureDate())) {


						if( airTicketInfoValidator.isValidStatus(airTicketInfo.getStatus())) {
						
								logger.info("In service layer, airTicketInfo object is valid");
								return true;
						
						}
					}
				}
			}
		}return false;


	}

	@Override
	public String addAirTicketInfo(AirTicketInfo airTicketInfo) throws  AirTicketInfoException {
		try {

			if(validateAirTicketInfo(airTicketInfo )) {
				logger.info("airticketinfo object is valid");
				return airTicketInfoDao.addAirTicketInfo(airTicketInfo);
			}else { 
				logger.warn("Invalid ticketinfo Details");
				return null;
			}
		}catch(SQLException e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}
	}


	@Override
	public String updateAirTicketInfo(AirTicketInfo airTicketInfo) throws AirTicketInfoException{
		try {
			return  airTicketInfoDao.updateAirTicketInfo(airTicketInfo);	

		}catch(SQLException e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}

	}

	@Override
	public  AirTicketInfo getAirTicketInfoByTicketId( String ticketId) throws AirTicketInfoException {
		try {
			return airTicketInfoDao.getAirTicketInfoByTicketId(ticketId);	

		}catch(SQLException e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}
	}

	@Override
	public List<AirTicketInfo> getAllAirTicketInfoByTicketId() throws AirTicketInfoException {
		try {
			return airTicketInfoDao.getAllAirTicketInfoByTicketId();	

		}catch(SQLException e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}
	}

	@Override
	public String deleteAirTicketInfo(String ticketId) throws AirTicketInfoException {
		try {
			return airTicketInfoDao.deleteAirTicketInfo(ticketId);	

		}catch(SQLException e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}catch(Exception e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}


	}

	@Override
	public String getLatestAirTicket() throws AirTicketInfoException {
		try {
			return airTicketInfoDao.getLatestAirTicket();

		} catch(SQLException e) {
			throw new AirTicketInfoException(e.getMessage(),e);

		} catch(Exception e) {
			throw new AirTicketInfoException(e.getMessage(),e);
		}
	}
}
