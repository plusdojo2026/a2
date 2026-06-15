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

        // 表示する年月（パラメータ or デフォルト：今月）
        String yearParam = request.getParameter("year");
        String monthParam = request.getParameter("month");

        LocalDate today = LocalDate.now();
        int year = (yearParam == null) ? today.getYear() : Integer.parseInt(yearParam);
        int month = (monthParam == null) ? today.getMonthValue() : Integer.parseInt(monthParam);

        // 1日の曜日（0=日曜〜6=土曜）
        LocalDate first = LocalDate.of(year, month, 1);
        int startDay = first.getDayOfWeek().getValue(); // 1=月曜〜7=日曜
        startDay = (startDay == 7) ? 0 : startDay;      // 日曜を0に変換

        // 月末の日付
        int lastDay = first.lengthOfMonth();

        // DAOからスタンプ一覧を取得
        StoragesDao dao = new StoragesDao();
        String yearMonth = String.format("%04d-%02d", year, month);
        Map<String, Integer> stampMap = dao.getStampByMonth(userId, yearMonth);

        // カレンダーの日付リストを作成
        List<DayData> dayList = new ArrayList<>();
        for (int d = 1; d <= lastDay; d++) {
            LocalDate date = LocalDate.of(year, month, d);
            dayList.add(new DayData(d, date.toString())); // 例:"2026-07-10"
        }

        int currentYear = LocalDate.now().getYear();

        // JSPに渡す
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("startDay", startDay);
        request.setAttribute("lastDay", lastDay);
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
