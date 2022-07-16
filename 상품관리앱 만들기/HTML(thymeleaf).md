```java
//상세 페이지!
// th:value="${item.id}"
모델에 있는 item 정보를 획득하고 프로퍼티 접근법으로 출력한다. ( item.getId() )
value 속성을 th:value 속성으로 변경한다.

<!DOCTYPE HTML>
<html  xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="utf-8">
  <link href="../css/bootstrap.min.css"
        th:href="@{/css/bootstrap.min.css}"
        rel="stylesheet">
  <style>
    .container {
      max-width: 560px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="py-5 text-center">
    <h2>상품 상세</h2>
  </div>
  <div>
    <label for="itemId">상품 ID</label>
    <input type="text" id="itemId" name="itemId" class="form-control" th:value="${item.id}"
           value="1" readonly>
  </div>
  <div>
    <label for="itemName">상품명</label>
    <input type="text" id="itemName" name="itemName" class="form-control" th:value="${item.itemName}"
           value="상품A" readonly>
  </div>
  <div>
    <label for="price">가격</label>
    <input type="text" id="price" name="price" class="form-control" th:value="${item.price}"
           value="10000" readonly>
  </div>
  <div>
    <label for="quantity">수량</label>
    <input type="text" id="quantity" name="quantity" class="form-control" th:value="${item.quantity}"
           value="10" readonly>
  </div>
  <hr class="my-4">
  <div class="row">
    <div class="col">
      <button class="w-100 btn btn-primary btn-lg"
              onclick="location.href='editForm.html'"
              th:onclick="|location.href='@{/basic/items/{itemId}/edit(itemId=${item.id})}'|"
              type="button">상품 수정</button>
    </div>

    <div class="col">
      <button class="w-100 btn btn-secondary btn-lg"

              th:onclick="|location.href='@{/basic/items}'|"
              type="button">목록으로</button>
    </div>
  </div>
</div> <!-- /container -->
</body>
</html>



//상품 목록 
