<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>モーダル表示サンプル</title>
  <style>
    /* モーダルの背景（暗い部分） */
    .modal-background {
      display: none; /* 最初は非表示 */
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,0.5);
      z-index: 10;
    }

    /* モーダルの本体 */
    .modal-content {
      background-color: white;
      width: 300px;
      margin: 100px auto;
      padding: 20px;
      border-radius: 10px;
      text-align: center;
      z-index: 11;
    }

    /* 閉じるボタン */
    .close-btn {
      margin-top: 10px;
    }
  </style>
</head>
<body>

  <h2>モーダル表示サンプル</h2>
  <button onclick="openModal()">開く</button>

  <!-- モーダル本体 -->
  <div id="modal" class="modal-background">
    <div class="modal-content">
      <p>これはモーダルです。</p>
      <h2>元気ですか？</h2>
      <input type="text" name="text" value="aaaa"><br>
      <button class="close-btn" onclick="closeModal()">閉じる</button>
    </div>
  </div>

  <script>
    // モーダル表示
    function openModal() {
      document.getElementById("modal").style.display = "block";
    }

    // モーダル非表示
    function closeModal() {
      document.getElementById("modal").style.display = "none";
    }
  </script>

</body>
</html>
