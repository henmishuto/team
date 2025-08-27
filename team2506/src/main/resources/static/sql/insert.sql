SET foreign_key_checks=1;

USE teamdb;

INSERT INTO mst_user
(user_name, password, family_name, first_name, family_name_kana, first_name_kana, gender)
VALUES ('taro@gmail.com', '111111', '山田', '太郎', 'やまだ', 'たろう', 0);

INSERT INTO mst_category (category_name,category_description) VALUES
('トップス', 'トップスのカテゴリーです'),
('ボトムス', 'ボトムスのカテゴリーです'),
('シューズ', 'シューズのカテゴリーです');


INSERT INTO mst_product(product_name,product_name_kana,product_description,category_id,price,image_full_path,release_date,release_company)VALUES 
('大人用パーカー','おとなようぱーかー','白色の大人用パーカーです。',1,6980,'/img/adult_shirts.jpg','2025/08/15','全国のセブンイレブン'),
('childcloth','ちゃいるどくろす','子供服です。',1,1500,'/img/kids_shirts.jpg','2025/08/19','UNIQLO'),
('感動イージーパンツ　大人用','かんどういーじーぱんつ　おとなよう','前ジッパーや腰ゴム、ベルトループが備わっていることにより、どのような体型の方でも綺麗に着用が可能。',2,3990,'/img/adult_bottoms.jpg','2025/09/01','ユニクロ'),
('感動イージーパンツ　子供用','かんどういーじーぱんつ　こどもよう','子供用ズボンです。',2,1500,'/img/kids_buttoms.jpg','2025/08/19','UNIQLO'),
('ニューバランス','にゅーばらんす','ニューバランスは、軽量で通気性に優れた大人向けのスニーカーです。カジュアルからスポーツシーンまで幅広く対応するスタイリッシュなデザインで、3色展開（ブラック、ホワイト、ネイビー）。',3,14800,'/img/adult_shoes.jpg','2025/10/15','全国のABCマート、公式オンラインストア、一部セレクトショップ'),
('キッズシューズ','きっずしゅーず','子供用シューズです。',3,2000,'/img/kids_shoes.jpg','2025/08/19','シューズストア');
