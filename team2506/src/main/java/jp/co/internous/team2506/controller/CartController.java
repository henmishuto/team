package jp.co.internous.team2506.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.team2506.model.domain.TblCart;
import jp.co.internous.team2506.model.domain.dto.CartDto;
import jp.co.internous.team2506.model.form.CartForm;
import jp.co.internous.team2506.model.mapper.TblCartMapper;
import jp.co.internous.team2506.model.session.LoginSession;


/**
 * カート情報に関する処理のコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/team2506/cart")
public class CartController {
	
	/*
	 * フィールド定義
	 */
	@Autowired
	private TblCartMapper cartMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	private Gson gson = new Gson();

	/**
	 * カート画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/")
	public String index(Model m) {
		int userId;
		
		if (loginSession.getUserId() != 0) {    
	        userId = loginSession.getUserId();
	    } else {
	        userId = loginSession.getTmpUserId();
	    }
		
		List<CartDto> cartList = cartMapper.findByUserId(userId);
		
		m.addAttribute("carts", cartList);
		m.addAttribute("loginSession", loginSession);
		
		return "cart";
	}

	/**
	 * カートに追加処理を行う
	 * @param f カート情報のForm
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {
		int userId;
		
	    if (loginSession.getUserId() != 0) {
	    	userId = loginSession.getUserId();
	    } else {
	    	userId = loginSession.getTmpUserId();
	    }
	    
	    List<CartDto> cartList = cartMapper.findByUserId(userId);
	    
	    CartDto existing = null;
	    for (CartDto c : cartList) {
	    	if (c.getProductId() == f.getProductId()) {
	    		existing = c;
	    		break;
	    	}
	    }
	    
	    if (existing != null) {
	    	
	    	int newCount = existing.getProductCount() + f.getProductCount();
	    	
	    	TblCart cart = new TblCart();
	    	cart.setUserId(userId);
	    	cart.setProductId(f.getProductId());
	    	cart.setProductCount(newCount);
	    	
	    	cartMapper.update(cart);
	    
	    } else {
	    	TblCart newCart = new TblCart();
	        newCart.setUserId(userId);
	        newCart.setProductId(f.getProductId());
	        newCart.setProductCount(f.getProductCount());
	        cartMapper.insert(newCart);
	    }	
	    
	    List<CartDto> newCartList = cartMapper.findByUserId(userId);
	    
	    m.addAttribute("carts", newCartList);
	    m.addAttribute("loginSession", loginSession);
	    
	    return "cart";
	}

	/**
	 * カート情報を削除する
	 * @param checkedIdList 選択したカート情報のIDリスト
	 * @return true:削除成功、false:削除失敗
	 */
	@PostMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		try {
			Map <String, List<String>> map = gson.fromJson(checkedIdList, Map.class);
			List <String> Ids = map.get("checkedIdList");
			List <Integer> checkedIds = new ArrayList<>();
			
			for (String s : Ids) {
				checkedIds.add(Integer.parseInt(s));
			}			
			
			int deleateRows = cartMapper.deleteById(checkedIds);
			
			return deleateRows > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
