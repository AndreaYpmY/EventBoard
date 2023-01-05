package it.sad.students.eventboard.persistenza.dao.postgresDao;

import it.sad.students.eventboard.persistenza.IdBroker;
import it.sad.students.eventboard.persistenza.dao.ReportDao;
import it.sad.students.eventboard.persistenza.model.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDaoPostgres implements ReportDao {

    Connection conn;

    public ReportDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Report> findAll() {
        ArrayList<Report> reports = new ArrayList<>();
        String query ="select * from report";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Report report = readEvent(rs);
                if(report!=null)
                    reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }



    @Override
    public Report findByPrimaryKey(Long id) {
        String query ="select * from report where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            stmt.setLong(1, id);

            if(rs.next()){
                Report report = readEvent(rs);
                if(report!=null)
                    return report;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveOrUpdate(Report report) {
        String insertReport = "INSERT INTO report VALUES (?, ?, ?, ?,?,?)";
        String updateReport = "UPDATE report set type=?, status=?, message = ?, date =?,person=? where id = ?";
        PreparedStatement st = null;

        try {
            if (report.getId() == null){
                st = conn.prepareStatement(insertReport);
                Long newId = IdBroker.getNewReportID(conn);
                report.setId(newId);
                st.setLong(1, report.getId());
                st.setLong(2, report.getType());
                st.setBoolean(3, report.getStatus());
                st.setString(4,report.getMessage());
                st.setDate(5, (Date) report.getDate());
                st.setLong(6,report.getPerson());

            }else {
                st = conn.prepareStatement(updateReport);
                st.setLong(1, report.getType());
                st.setBoolean(2, report.getStatus());
                st.setString(3,report.getMessage());
                st.setDate(4, (Date) report.getDate());
                st.setLong(5,report.getPerson());
                st.setLong(6, report.getId());

            }

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Report report) {
        String query = "DELETE FROM report WHERE id = ?";
        try {
            // TODO: 30/12/2022 rimozione a cascata
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, report.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Report readEvent(ResultSet rs) {
        try{
            Report report = new Report();
            report.setId(rs.getLong("id"));
            report.setType(rs.getLong("type"));
            report.setStatus(rs.getBoolean("status"));
            report.setMessage(rs.getString("message"));
            report.setDate(rs.getDate("date"));
            report.setPerson(rs.getLong("person"));
            return report;
        }catch (SQLException e){e.printStackTrace();}

        return null;
    }
}
