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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.StoragesDao;
import dao.TrItemsDao;
import dto.Storage;
import dto.TrItem;
import dto.User;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	// セッションからユーザー情報を取得
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	// ログインしていない場合はログイン画面に飛ばす
    	if (user == null) {
    		response.sendRedirect("/a2/LoginServlet");
    		return;
    	}
    	// ログイン中のユーザーIDを取得
    	String userId = user.getUserId();

        // 表示する年月（パラメータないときデフォルトで今月）
        String yearParam = request.getParameter("year");
        String monthParam = request.getParameter("month");

        LocalDate today = LocalDate.now();
        int year;
        if (yearParam == null) {
            year = today.getYear();
        } else {
            year = Integer.parseInt(yearParam);
        }

        int month;
        if (monthParam == null) {
            month = today.getMonthValue();
        } else {
            month = Integer.parseInt(monthParam);
        }

        // 1日の曜日（0=日曜〜6=土曜）
        LocalDate first = LocalDate.of(year, month, 1);
        int startDay = first.getDayOfWeek().getValue(); // 1=月曜〜7=日曜
        if (startDay == 7) {
            startDay = 0;	// 日曜を0に変換
        }

        // 月末の日付
        int lastDay = first.lengthOfMonth();

        // DAOからスタンプ一覧を取得
        StoragesDao dao = new StoragesDao();
        String yearMonth = String.format("%04d-%02d", year, month);
        Map<String, Integer> stampMap = dao.getStampByMonth(userId, yearMonth);
        // メモ内容の取得
        Map<String, String> memoMap = dao.getMemo(userId, yearMonth);
        
        int currentYear = LocalDate.now().getYear();
        
        
        // --- ループの外で一括取得 ---
        Map<String, List<Storage>> trainingMap = dao.getTrainingByMonth(userId, yearMonth); // 1回だけSQL実行
        Map<String, Double> weightMap = dao.getWeightByMonth(userId, yearMonth);
        Map<String, Double> fatMap = dao.getFatByMonth(userId, yearMonth);

        List<DayData> dayList = new ArrayList<>();
        for (int d = 1; d <= lastDay; d++) {
            LocalDate date = LocalDate.of(year, month, d);
            String fullDate = date.toString();

            dayList.add(new DayData(d, fullDate));

        }
        TrItemsDao trItemDao = new TrItemsDao();
        List<TrItem> itemList = trItemDao.getAllTrainingItems();
        
        // Gsonインスタンスの生成
        Gson gson = new Gson();
        
        // JavaのリストやマップをJSON文字列に変換
        String itemListJson = gson.toJson(itemList);
        String trainingMapJson = gson.toJson(trainingMap);
        
        // JSPに渡す
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("startDay", startDay);
        request.setAttribute("lastDay", lastDay);
        request.setAttribute("dayList", dayList);
        request.setAttribute("stampMap", stampMap);
        request.setAttribute("currentYear", currentYear);
        request.setAttribute("memoMap", memoMap);
        request.setAttribute("trainingMapJson", trainingMapJson);
        request.setAttribute("itemListJson", itemListJson);
        request.setAttribute("weightMap", weightMap);
        request.setAttribute("fatMap", fatMap);

        request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp").forward(request, response);
    }

    
    // 記録編集
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// セッションからユーザー情報を取得
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	// ログインしていない場合はログイン画面に飛ばす
    	if (user == null) {
    		response.sendRedirect("/a2/LoginServlet");
    		return;
    	}
    	// ログイン中のユーザーIDを取得
    	String userId = user.getUserId();

        request.setCharacterEncoding("UTF-8");
        StoragesDao dao = new StoragesDao();
        
        // 処理の判定用パラメータを取得
        String action = request.getParameter("action");

        if (action == null) {
            // 既存の「スタンプ・体重・全体メモ」の更新
            String date = request.getParameter("date");
            int stamp = Integer.parseInt(request.getParameter("stamp"));
            String comments = request.getParameter("comments");
            double weight = Double.parseDouble(request.getParameter("weight"));
            // 体脂肪率の取得
            String fatStr = request.getParameter("fat");
            double fat = (fatStr == null || fatStr.isEmpty()) ? 0.0 : Double.parseDouble(fatStr);
            
            dao.saveRecord(userId, date, stamp, comments, weight, fat);

        } else if (action.equals("update")) {
            // トレーニング内容の「変更」
            Storage s = new Storage();
            s.setId(Integer.parseInt(request.getParameter("id")));
            s.setTr_id(Integer.parseInt(request.getParameter("tr_id")));
            s.setTr_weight(Integer.parseInt(request.getParameter("tr_weight")));
            s.setCounts(Integer.parseInt(request.getParameter("counts")));
            s.setSets(Integer.parseInt(request.getParameter("sets")));
            s.setMemo(request.getParameter("tr_memo"));

            dao.updateTraining(s);

        } else if (action.equals("delete")) {
            // トレーニング内容の「削除」
            int id = Integer.parseInt(request.getParameter("id"));
            
            dao.deleteTraining(id);
        } else if (action.equals("insert")) {
            // トレーニング内容の「新規」
        	String date = request.getParameter("date");
        	Storage s = new Storage();
            s.setTr_id(Integer.parseInt(request.getParameter("tr_id")));
            s.setTr_weight(Integer.parseInt(request.getParameter("tr_weight")));
            s.setCounts(Integer.parseInt(request.getParameter("counts")));
            s.setSets(Integer.parseInt(request.getParameter("sets")));
            s.setMemo(request.getParameter("tr_memo"));
            
            dao.insertTraining(userId, date, s);
        }

        // カレンダーへリダイレクト
        response.sendRedirect("/a2/CalendarServlet");
    }
    
    
    // 日付データをまとめる内部クラス
    public static class DayData {
        public int day;
        public String fullDate;

        public DayData(int day, String fullDate) {
            this.day = day;
            this.fullDate = fullDate;
        }

        // JSPが読むためのgetter
        public int getDay() {
            return day;
        }

        public String getFullDate() {
            return fullDate;
        }
    }
}
