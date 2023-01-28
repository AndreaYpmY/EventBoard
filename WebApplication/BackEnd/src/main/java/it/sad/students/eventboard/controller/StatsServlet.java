package it.sad.students.eventboard.controller;

import it.sad.students.eventboard.persistenza.DBManager;
import it.sad.students.eventboard.persistenza.model.GlobalStats;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/getstats")
public class StatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GlobalStats globalStats = new GlobalStats();
        globalStats.setNEvents(DBManager.getInstance().getEventDao().findAll().size());
        globalStats.setNComments(DBManager.getInstance().getCommentDao().findAll().size());
        globalStats.setNReviews(DBManager.getInstance().getReviewDao().findAll().size());
        globalStats.setNLike(DBManager.getInstance().getLikeDao().findAll().size());
        globalStats.setNPartecipation(DBManager.getInstance().getPartecipationDao().findAll().size());
        req.setAttribute("global",globalStats);

        // TODO: 28/01/2023 lista top 5


        req.setAttribute("topFive",list);
        RequestDispatcher dispacher = req.getRequestDispatcher("views/stats.html");
        dispacher.forward(req, resp);
    }
}
