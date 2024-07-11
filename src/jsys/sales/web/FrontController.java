/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * FrontController.java
 *
 */
package jsys.sales.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsys")
public class FrontController extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 * 										HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException {
		String page = null;

		// 画面のボタンIDを取得する。
		request.setCharacterEncoding("UTF-8");
		String buttonID = request.getParameter("BUTTON_ID");

		// ボタンIDがnullの場合、デフォルト値を設定する。
		if (buttonID == null) {
			buttonID = "UC999_01";
		}

		// どのボタンが押されたかによって処理を振り分ける。
		switch(buttonID) {
			// メニュー画面の商品検索ボタンが押された場合
			// 商品検索結果画面の前画面に戻るボタンが押された場合
			case "UC000_01_01":
				page = "/SearchItemView.jsp";
				break;
			// 商品検索画面の検索ボタンが押された場合
			case "UC000_01_02":
				SearchItemAction action = new SearchItemAction();
				page = action.execute(request);
				break;
			// メニューに戻るボタンが押された場合
			case "UC999_01":
				page = "Menu.html";
				break;

				//メニュー画面の得意先検索ボタンが押された場合
				//得意先検索画面の前画面に戻るボタンが押された場合
			case "UC000_02_01":
				page= "/SearchCustomerView.jsp";
				break;

				// 得意先検索画面の検索ボタンが押された場合
			case "UC000_02_02":
					SearchCustomerAction customeraction = new SearchCustomerAction();
					page = customeraction.execute(request);
					break;
		}

		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
