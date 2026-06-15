package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	// 年の取得（なければ今年）
    	String y = request.getParameter("year");
    	int year;
    	if (y == null) {
    	    year = LocalDate.now().getYear();
    	} else {
    	    year = Integer.parseInt(y);
    	}

        // 月の取得（なければ今月）
        String m = request.getParameter("month");
        int month;
        if (m == null) {
            month = LocalDate.now().getMonthValue();
        } else {
            month = Integer.parseInt(m);
        }

        // その月の1日
        LocalDate firstDay = LocalDate.of(year, month, 1);

        // 曜日（0が日曜、6が土曜）
        int startDay = firstDay.getDayOfWeek().getValue(); // 月が1、日が7
        startDay = (startDay == 7) ? 0 : startDay; // 日曜を0に変換

        // 月末日
        int lastDay = firstDay.lengthOfMonth();
        
        // 今何年か（2026）
        int currentYear = LocalDate.now().getYear();

        // JSPに渡す
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("startDay", startDay);
        request.setAttribute("lastDay", lastDay);
        request.setAttribute("currentYear", currentYear);

        // JSPへフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp").forward(request, response);
    }
}
