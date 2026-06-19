/*データベース作成*/
CREATE DATABASE a2;

USE a2;

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
INSERT INTO users (user_name, height, target_weight, logical_delete, user_id, password)
VALUES
('テスト太郎', 170.5, 60.0, 0, 'user1', 'pass1'),
('サンプル花子', 158.2, 50.0, 0, 'user2', 'pass2');

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
INSERT INTO tr_storages (user_id,tr_id,tr_weight, counts,sets,memo) VALUES
('user1',1,20,12,4,''),
('user1',3,20,12,3,''),
('user1',2,20,12,2,''),
('user2',2,20,13,1,''),
('user2',2,20,14,4,''),
('user2',3,20,14,3,''),
('user2',4,20,15,2,'');

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
INSERT INTO friends (user_id,friend_user_id) VALUES
('user1','user2'),
('user2','user1');

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
(1, '温泉卵のっけえだまめ​', 'img/recipe1.jpg'),
(2, '枝豆と鶏むねのプロテインサラダ', 'img/recipe2.jpg'),
(3, '黒豆とオートミールの朝食ボウル', 'img/recipe3.jpg');

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
(5, '小さな積み重ねが大きな力に！');

/* 1ユーザーにつき1日1回しか登録できない仕組みを作る文 */
ALTER TABLE storages ADD UNIQUE KEY unique_user_date (user_id, date);
