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
CREATE TABLE groups (
    group_id INT AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(15) NOT NULL,
    tr_id INT
);
/* グループテーブルインサート */
INSERT INTO groups (group_name, tr_id) VALUES
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
    group_id INT PRIMARY KEY,
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
