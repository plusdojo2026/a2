/*データベース作成*/
--CREATE DATABASE a2;

--USE a2;

/*ユーザー情報*/
CREATE TABLE users(
number INT AUTO_INCREMENT PRIMARY KEY,	/*管理番号*/
user_name VARCHAR(100) NOT NULL,		/*ユーザー名*/
height DOUBLE NOT NULL,				/*身長*/
gender VARCHAR(8),						/*性別*/
target_weight DOUBLE,					/*目標体重*/
logical_delete BIT DEFAULT 0,			/*論理削除*/
user_id VARCHAR(30) NOT NULL,			/*ユーザーID*/
password VARCHAR(30) NOT NULL,			/*パスワード*/
icon_id	INT DEFAULT 0,					/*アイコンID*/
design_id INT DEFAULT 0,				/*背景ID*/
point INT DEFAULT 0						/*豆ポイント*/
);
/*内容入力*/
INSERT INTO users (user_name, height, target_weight,point, logical_delete, user_id, password)
VALUES
('テスト太郎', 170.5, 60.0,600, 0, 'user1', 'pass1'),
('サンプル花子', 158.2, 50.0,125, 0, 'user2', 'pass2'),
('山田次郎', 175.0, 68.0,560, 0, 'user3', 'pass3'),
('佐藤美咲', 162.3, 52.5,1200, 0, 'user4', 'pass4'),
('鈴木健太', 180.0, 75.0, 70,0, 'user5', 'pass5'),
('高橋あかり', 155.0, 48.0,40, 0, 'user6', 'pass6'),
('伊藤翔', 168.8, 65.0, 450,0, 'user7', 'pass7'),
('渡辺優奈', 165.5, 54.0,15, 0, 'user8', 'pass8'),
('中村大輔', 172.2, 70.0, 90,0, 'user9', 'pass9'),
('小林さくら', 160.0, 49.5,50, 0, 'user10', 'pass10');

/*記録情報*/
CREATE TABLE storages(
storage_id INT AUTO_INCREMENT PRIMARY KEY,	/*記録情報*/
user_id VARCHAR(30) NOT NULL,				/*ユーザーID*/
weight DOUBLE,								/*体重*/
fat DOUBLE,									/*体脂肪率*/
comments VARCHAR(100),							/*メモ*/
stamp INT,									/*スタンプ*/
date DATE DEFAULT (CURRENT_DATE)			/*日付*/
);
/*内容入力*/
INSERT INTO storages (user_id, weight, fat, comments, stamp, date) VALUES
('user1', 60.5, 20.1, '胸トレ', 1, '2026-06-01'),
('user1', 60.3, 20.0, '背中トレ', 2, '2026-06-03'),
('user1', 60.2, 19.8, '脚トレ', 3, '2026-06-05'),
('user1', 60.1, 19.7, '休息日', 0, '2026-06-10'),
('user1', 60.0, 19.6, '肩トレ', 1, '2026-06-15'),
('user1', 59.8, 19.5, '腕トレ', 2, '2026-06-20'),
('user1', 59.7, 19.4, '胸トレ', 3, '2026-06-25');

/*トレーニング内容（記録情報）*/
CREATE TABLE tr_storages(
id INT AUTO_INCREMENT PRIMARY KEY,	/*TR内容保存ID*/
user_id VARCHAR(30) NOT NULL,		/*ユーザーID*/	
tr_id INT NOT NULL,					/*トレーニングID*/
tr_weight INT,						/*重さ（距離）*/
counts INT,							/*回数*/
sets INT,							/*セット*/
memo VARCHAR(100),					/*メモ*/
date DATE DEFAULT (CURRENT_DATE)	/*日付*/
);
/*内容入力*/
INSERT INTO tr_storages (user_id, tr_id, tr_weight, counts, sets, memo, date) VALUES

-- user1（成長ログ）
('user1', 1, 30, 10, 3, 'ベンチ開始', '2026-06-01'),
('user1', 1, 35, 10, 3, '少し余裕', '2026-06-03'),
('user1', 1, 40, 8, 3, '重量UP', '2026-06-05'),
('user1', 2, 0, 20, 4, 'スクワット自重', '2026-06-14'),
('user1', 2, 20, 15, 4, '軽負荷追加', '2026-06-14'),
('user1', 3, 50, 10, 3, 'ラットプル開始', '2026-06-14'),
('user1', 3, 60, 8, 3, '背中強化', '2026-06-14'),
('user1', 4, 5, 30, 2, '腹筋', '2026-06-14'),
('user1', 4, 6, 40, 3, '腹筋耐久', '2026-06-14'),

-- user2（安定系）
('user2', 1, 20, 12, 3, '軽めベンチ', '2026-06-01'),
('user2', 1, 25, 12, 3, 'フォーム意識', '2026-06-04'),
('user2', 2, 0, 15, 3, '自重スクワット', '2026-06-05'),
('user2', 2, 10, 15, 3, 'ダンベル追加', '2026-06-07'),
('user2', 3, 30, 12, 3, '背中トレ', '2026-06-09'),
('user2', 4, 12, 50, 2, '腹筋メイン', '2026-06-11'),

-- user3（増量・高重量）
('user3', 1, 60, 5, 5, '高重量開始', '2026-06-01'),
('user3', 1, 65, 5, 5, '重量アップ', '2026-06-03'),
('user3', 1, 70, 4, 5, '限界挑戦', '2026-06-10'),
('user3', 3, 80, 6, 4, '背中強化', '2026-06-10'),
('user3', 3, 85, 5, 4, 'さらに追い込み', '2026-06-10'),

-- user4（有酸素混合）
('user4', 2, 0, 30, 3, 'ランニング', '2026-06-01'),
('user4', 2, 0, 35, 3, '距離延長', '2026-06-03'),
('user4', 2, 0, 40, 3, '持久力UP', '2026-06-06'),
('user4', 4, 9, 20, 3, '腹筋＋有酸素', '2026-06-06');

/*一時保存*/
CREATE TABLE saves(
storage_id INT AUTO_INCREMENT PRIMARY KEY,	/*記録情報*/
user_id VARCHAR(30) NOT NULL,				/*ユーザーID*/
weight DOUBLE,								/*体重*/
fat DOUBLE,									/*体脂肪率*/
comments VARCHAR(100),							/*メモ*/
stamp INT,									/*スタンプ*/
date DATE DEFAULT (CURRENT_DATE)			/*日付*/
);

/*トレーニング内容（一時保存）*/
CREATE TABLE tr_saves(
id INT AUTO_INCREMENT PRIMARY KEY,			/*TR内容保存ID*/
user_id VARCHAR(30) NOT NULL,				/*ユーザーID*/	
tr_id INT NOT NULL,							/*トレーニングID*/
tr_weight INT,								/*重さ（距離）*/
counts INT,									/*回数*/
sets INT,									/*セット*/
memo VARCHAR(100),							/*メモ*/
date DATE DEFAULT (CURRENT_DATE)			/*日付*/
);

/*フレンド*/
CREATE TABLE friends(
friend_id INT AUTO_INCREMENT PRIMARY KEY,	/*フレンドID*/
user_id	VARCHAR(30) NOT NULL,				/*ユーザーID*/
friend_user_id VARCHAR(30),					/*フレンドのユーザーID*/
friend_request BIT DEFAULT 0				/*申請の承認フラグ*/
);
/*内容入力*/
INSERT INTO friends (user_id, friend_user_id,friend_request) VALUES
-- user1（多め・中心ユーザー）
('user1', 'user2',1),
('user1', 'user3',1),
('user4', 'user1',1),
('user5', 'user1',1),
('user6', 'user1',0),
('user7', 'user1',0),
('user1', 'user8',0),
('user1', 'user9',0),

-- その他のユーザー関係
('user2', 'user3',0),
('user2', 'user5',1),
('user3', 'user4',1),
('user3', 'user6',1),
('user4', 'user5',1),
('user5', 'user6',1),
('user6', 'user7',1),
('user7', 'user8',1),
('user8', 'user9',1),
('user9', 'user10',1),
('user10', 'user2',1);


/*ログイン記録*/
CREATE TABLE logs(
log_id INT AUTO_INCREMENT PRIMARY KEY,			/*ログイン記録ID*/
user_id	VARCHAR(30),							/*ユーザーID*/
date_time DATETIME DEFAULT (CURRENT_TIMESTAMP)	/*時間付き日付*/
);

/*トレーニング項目*/
CREATE TABLE tr_items(
tr_id INT AUTO_INCREMENT PRIMARY KEY,			/*トレーニングID*/
tr_item	VARCHAR(50) NOT NULL					/*トレーニング項目*/
);
/*内容入力*/
INSERT INTO tr_items (tr_item) VALUES
('ベンチプレス'),
('インクラインベンチプレス'),
('ダンベルベンチプレス'),
('インクラインダンベルプレス'),
('ダンベルフライ'),
('ケーブルクロスオーバー'),
('チェストプレス'),
('ペックデックフライ'),
('インクラインチェストプレス'),
('ペックフライ'),

('ラットプルダウン'),
('フロントプルダウン'),
('シーテッドロー'),
('ベントオーバーロウ'),
('ワンハンドダンベルロウ'),
('Tバーロウ'),
('ケーブルロウ'),
('デッドリフト'),
('アップライトロウ'),

('ショルダープレス'),
('ダンベルショルダープレス'),
('アーノルドプレス'),
('サイドレイズ'),
('フロントレイズ'),
('リアレイズ'),
('ケーブルサイドレイズ'),
('フェイスプル'),
('リアデルト'),
('シュラッグ'),

('バーベルカール'),
('ダンベルカール'),
('ハンマーカール'),
('プリーチャーカール'),
('トライセプスプッシュダウン'),
('オーバーヘッドエクステンション'),
('スカルクラッシャー'),
('クローズグリップベンチプレス'),

('スクワット'),
('フロントスクワット'),
('ブルガリアンスクワット'),
('レッグプレス'),
('レッグエクステンション'),
('レッグカール'),
('ヒップスラスト'),
('カーフレイズ'),

('アブドミナル'),
('ロータリートルソ'),
('インナーサイ(アダクター)'),
('アウターサイ(アブダクター)'),
('ケーブルクランチ'),
('ケーブルプレスダウン');

/* アイコンテーブル作成 */
CREATE TABLE icons (
    icon_id INT AUTO_INCREMENT PRIMARY KEY,
    icon INT
);
/* アイコンテーブルインサート */
INSERT INTO icons (icon) VALUES
(1),
(2),
(3),
(4),
(5);

/* 背景テーブル作成 */
CREATE TABLE backgrounds (
    background_id INT AUTO_INCREMENT PRIMARY KEY,
    background INT
);
/* 背景テーブルインサート */
INSERT INTO backgrounds (background) VALUES
(1),
(2),
(3),
(4),
(5);

/* グループテーブル作成 */
CREATE TABLE group_list (
    group_id INT AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(15) NOT NULL,
    tr_id INT
);
/* グループテーブルインサート */
INSERT INTO group_list (group_name, tr_id) VALUES
('さやえんどう', 1),
('イソフラボンボンボン', 2);

/* グループ中間テーブル作成 */
CREATE TABLE group_connects (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    group_id INT,
    user_id VARCHAR(30) NOT NULL,
    number BIT(1) DEFAULT 0
);
/* グループ中間テーブルインサート */
INSERT INTO group_connects (group_id, user_id, number) VALUES
(1, 'user1', 1),
(1, 'user2', 1),
(1, 'user3', 1),
(2, 'user4', 1),
(2, 'user5', 1),
(2, 'user6', 0);


/* ランキングテーブル作成 */
CREATE TABLE rankings (
    ranking_id INT AUTO_INCREMENT PRIMARY KEY,
    group_id INT,
    max_record INT,
    tr_id INT NOT NULL,
    user_id VARCHAR(30)
);
/* ランキングテーブルインサート */
INSERT INTO rankings (group_id, max_record, tr_id, user_id) VALUES
(1, 120, 1, 'user1'),
(1, 150, 1, 'user2'),
(1, 200, 1, 'user3'),
(2, 130, 2, 'user4'),
(2, 100, 2, 'user5'),
(2, 180, 2, 'user6');

/* 豆知識テーブル作成 */
CREATE TABLE knowledges (
    knowledge_num INT AUTO_INCREMENT PRIMARY KEY,
    trivia VARCHAR(300) NOT NULL
);
/* 豆知識テーブルインサート */
INSERT INTO knowledges (trivia) VALUES
('お酒を飲むと​筋肉が分解されちゃうらしいよ！​'),
('お酒を飲むくらいなら​ずんだシェイクを飲め！！​'),
('豆類は食物繊維が多く、腸内環境を整える効果が期待できる。');

/* 豆レシピテーブル作成 */
CREATE TABLE recipes (
    recipe_number INT PRIMARY KEY,
    recipe VARCHAR(300),
    recipe_img VARCHAR(50)
);
/* 豆レシピテーブルインサート */
INSERT INTO recipes (recipe_number, recipe, recipe_img) VALUES
(1, '温泉卵のっけえだまめ​', '/a2/img/recipe1.png'),
(2, 'ピーナッツと塩昆布のピリ辛和え', '/a2/img/recipe2.png'),
(3, '黒豆のクリームチーズ和え​', '/a2/img/recipe3.png'),
(4, 'ミックスビーンズのツナマヨポン酢​', '/a2/img/recipe4.png'),
(5, '豆苗と大豆の塩昆布ナムル', '/a2/img/recipe5.png'),
(6, 'ひよこ豆の明太マヨ和え', '/a2/img/recipe6.png'),
(7, '枝豆塩昆布チーズ​', '/a2/img/recipe7.png'),
(8, 'スモークチキンと大豆のハニーマスタード和え', '/a2/img/recipe8.png'),
(9, '枝豆とちくわの塩昆布バター​', '/a2/img/recipe9.png'),
(10, 'ミックスビーンズとトマトのタコス風サラダ​', '/a2/img/recipe10.png');

/* 成長記録テーブル作成 */
CREATE TABLE words (
    word INT PRIMARY KEY,
    word_of_day VARCHAR(30) NOT NULL
);
/* 成長記録テーブルインサート */
INSERT INTO words (word, word_of_day) VALUES
(1, '今日も一歩前進だよ！'),
(2, '継続こそ最強の武器！'),
(3, '昨日の自分を超えよう！'),
(4, '無理なく、でも確実に！'),
(5, '小さな積み重ねが大きな力に！'),
(6, '歩くタンパク質！'),
(7, '縫い目が悲鳴上げてる！'),
(8, 'フィジカルがバグってる！'),
(9, 'その体、国が管理すべき資源！'),
(10, '身体の8割が広背筋！'),
(11, '筋肉が服を着るのを拒否してる！'),
(12, '肩にメロン4玉入ってる？'),
(13, '胸筋がドアより分厚い！'),
(14, '筋トレは、自分を裏切らない唯一の努力だ。'),
(15, '死ぬ気でやれよ、死なないから。'),
(16, 'NO PUMP, NO LIFE.（パンプなき人生などない）'),
(17, '言い訳を、筋肉に変えろ。'),
(18, '筋肉に、妥協の二文字はない。'),
(19, '筋肉が、お前の歴史だ。'),
(20, 'ウエイトが重いんじゃない。覚悟が軽いんだ。'),
(21, '甘えを捨てるか、夢を捨てるか。'),
(22, '迷ったら、重い方を挙げろ。'),
(23, '限界は、脳が作った幻だ。'),
(24, '明日やろうは、バカヤロウだ。'),
(25, 'その1レップに、魂を込めろ。'),
(26, 'やらない理由は、探せば無限にある。'),
(27, '言い訳は、カロリーを消費しない。'),
(28, '自分に勝てない奴が、誰に勝てる？'),
(29, '筋肉は、一日にして成らず。'),
(30, '痛いのは、成長している証拠だ。'),
(31, '昨日の自分を、今日超えろ。'),
(32, 'ただ、やるだけだ。（Just do it）'),
(33, '筋肉は、嘘をつかない。'),
(34, 'きつい時こそ、笑顔で筋トレ。'),
(35, '筋肉が、最高の服だ。'),
(36, 'あと1回。それが未来を変える。'),
(37, 'キツいんじゃない、効いているんだ。'),
(38, '汗は、脂肪が流す涙だ。'),
(39, '今日サボれば、明日泣く。'),
(40, '妥協した瞬間に、試合終了だ。');




/* 1ユーザーにつき1日1回しか登録できない仕組みを作る文 */
ALTER TABLE storages ADD UNIQUE KEY unique_user_date (user_id, date);
