package jp.co.internous.team2506.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jp.co.internous.team2506.model.domain.MstProduct;
import jp.co.internous.team2506.model.mapper.MstProductMapper;
import jp.co.internous.team2506.model.session.LoginSession;

/**
 * 商品情報の詳細画面を制御するコントローラー
 */
@Controller
@RequestMapping("/team2506/product")
public class ProductController {
	
	@Autowired
	private MstProductMapper productMapper;
	@Autowired
	private LoginSession loginSession;



	/**
	 * 商品詳細画面を表示する
	 * @param id 商品ID
	 * @param model 画面表示用オブジェクト
	 * @return 商品詳細テンプレート
	 */
	@GetMapping("/{id}")
	public String index(@PathVariable("id") int id, Model model) {
		
		MstProduct product = productMapper.findById(id);
		if (product == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "商品が見つかりませんでした");
		}
		

		model.addAttribute("product", product);
		model.addAttribute("loginSession", loginSession);
		

		return "product_detail";
	}
}