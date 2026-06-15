package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoragesDao;


@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        // ログイン中のユーザーID（本来はセッションから取得）
        String userId = "user1";

        // 表示する年月（パラメータorデフォルト：今月）
        String yearParam = request.getParameter("year");
        String monthParam = request.getParameter("month");

        LocalDate today = LocalDate.now();
        int year = (yearParam == null) ? today.getYear() : Integer.parseInt(yearParam);
        int month = (monthParam == null) ? today.getMonthValue() : Integer.parseInt(monthParam);

        // DAOからスタンプ一覧を取得
        StoragesDao dao = new StoragesDao();
        String yearMonth = String.format("%04d-%02d", year, month);
        Map<String, Integer> stampMap = dao.getStampByMonth(userId, yearMonth);

        // カレンダーの日付リストを作成
        List<DayData> dayList = new ArrayList<>();
        LocalDate first = LocalDate.of(year, month, 1);
        int length = first.lengthOfMonth();

        for (int d = 1; d <= length; d++) {
            LocalDate date = LocalDate.of(year, month, d);
            dayList.add(new DayData(d, date.toString())); // 例:"2026-07-10"
        }
        int currentYear = LocalDate.now().getYear();
        
        // JSPに渡す
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("dayList", dayList);
        request.setAttribute("stampMap", stampMap);
        request.setAttribute("currentYear", currentYear);

        request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp").forward(request, response);
    }

    // 日付データをまとめる内部クラス
    public static class DayData {
        public int day;
        public String fullDate;

        public DayData(int day, String fullDate) {
            this.day = day;
            this.fullDate = fullDate;
        }
    }
}
