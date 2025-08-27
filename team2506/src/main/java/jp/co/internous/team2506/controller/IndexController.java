package jp.co.internous.team2506.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.team2506.model.domain.MstCategory;
import jp.co.internous.team2506.model.domain.MstProduct;
import jp.co.internous.team2506.model.form.SearchForm;
import jp.co.internous.team2506.model.mapper.MstCategoryMapper;
import jp.co.internous.team2506.model.mapper.MstProductMapper;
import jp.co.internous.team2506.model.session.LoginSession;

/**
 * 商品検索に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/team2506")
public class IndexController {
	
	/*
	 * フィールド定義
	 */
	@Autowired
	private MstCategoryMapper categoryMapper;
	
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	/**
	 * トップページを初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		if(loginSession.getUserId() == 0 && loginSession.isLoggedIn() == false) {
			
			int guestId = 0;
			while (guestId == 0) {
				guestId = -(int)(Math.random() * 1_000_000_000);
			}
			loginSession.setTmpUserId(guestId);
			loginSession.setLoggedIn(true);
		}
		
		List<MstCategory> category = categoryMapper.findAll();
		m.addAttribute("categories", category);
		List<MstProduct> product = productMapper.findAll();
		m.addAttribute("products", product);
		
		m.addAttribute("loginSession", loginSession);
		
		return "index";
	}

	/**
	 * 検索処理を行う
	 * @param f 検索用フォーム
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/searchItem")
	public String searchItem(SearchForm f, Model m) {
		
		String keywords = f.getKeywords();
		int categoryId = f.getCategory();
		String normalized;
		String[] keywordsArray = null;
		
		if(keywords != null) {
			normalized = keywords.replace("　", " ").replaceAll(" {2,}", " ").trim();
			keywordsArray = normalized.split(" ");
		}else {
			normalized = null;
		}
		List<MstCategory> category = categoryMapper.findAll();
		m.addAttribute("categories", category);
		
		if (normalized == null && categoryId == 0) {
			List<MstProduct> product = productMapper.findAll();
			m.addAttribute("products", product);
		}else if(normalized == null && categoryId != 0){
			List<MstProduct> product = productMapper.findByCategoryAndProductName(categoryId, null);
			m.addAttribute("products", product);
		}else if(normalized != null && categoryId != 0) {
			List<MstProduct> product = productMapper.findByCategoryAndProductName(categoryId, keywordsArray);
			m.addAttribute("products", product);
		}else if(normalized != null && categoryId == 0) {
			List<MstProduct> product = productMapper.findByProductName(keywordsArray);
			m.addAttribute("products", product);
		}
		
		String newKeywords = String.join(" ", keywordsArray);
		
		m.addAttribute("keywords", newKeywords);
		m.addAttribute("selected", f.getCategory());
		m.addAttribute("loginSession", loginSession);
		
		return "index";
		
	}

}
