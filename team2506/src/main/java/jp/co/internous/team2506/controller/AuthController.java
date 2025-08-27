package jp.co.internous.team2506.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jp.co.internous.team2506.model.domain.MstUser;
import jp.co.internous.team2506.model.form.UserForm;
import jp.co.internous.team2506.model.mapper.MstUserMapper;
import jp.co.internous.team2506.model.mapper.TblCartMapper;
import jp.co.internous.team2506.model.session.LoginSession;


/**
 * 認証に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@RestController
@RequestMapping("/team2506/auth")
public class AuthController {
	
	/*
	 * フィールド定義
	 */
	@Autowired
	private LoginSession loginSession;
	@Autowired
	private MstUserMapper mstUserMapper;
	@Autowired
	private TblCartMapper cartMapper;
	
	private Gson gson = new Gson();
	
		
	/**
	 * ログイン処理をおこなう
	 * @param f ユーザーフォーム
	 * @return ログインしたユーザー情報(JSON形式)
	 */
	@PostMapping("/login")
	public String login(@RequestBody UserForm f) {
		MstUser user = mstUserMapper.findByUserNameAndPassword(f.getUserName(), f.getPassword());
		
		if (user != null ) {
			
			cartMapper.updateUserId(user.getId(),loginSession.getTmpUserId());
			
			loginSession.setUserId(user.getId());
			loginSession.setTmpUserId(0);
			loginSession.setUserName(f.getUserName());
			loginSession.setPassword(f.getPassword());
			loginSession.setLoggedIn(true);
			
			return gson.toJson(user);
			
		}else {
			
			return gson.toJson(user);
		}
		
	}
	
	/**
	 * ログアウト処理をおこなう
	 * @return 空文字
	 */
	@PostMapping("/logout")
	public String logout() {
		
		loginSession.setUserId(0);
		loginSession.setTmpUserId(0);
		loginSession.setUserName(null);
		loginSession.setPassword(null);
		loginSession.setLoggedIn(false);
		
		return "";
	}

	/**
	 * パスワード再設定をおこなう
	 * @param f ユーザーフォーム
	 * @return 処理後のメッセージ
	 */
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestBody UserForm f) {
		
		mstUserMapper.updatePassword (loginSession.getUserName(), f.getNewPassword ());
		loginSession.setPassword (f.getNewPassword ());
		if(f. getPassword ().equals(f.getNewPassword ())) {
			
			return "現在のパスワードと同一文字列が入力されました。";
		}else {
			
			return "パスワードが再設定されました。";
		}
	}
}
