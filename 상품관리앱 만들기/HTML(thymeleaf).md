타임리프 몇가지 사용 개념

핵심은 th:xxx 가 붙은 부분은 서버사이드에서 렌더링 되고, 기존 것을 대체한다. th:xxx 이 없으면 기존
html의 xxx 속성이 그대로 사용된다.

h:href="@{/css/bootstrap.min.css}"
@{...} : 타임리프는 URL 링크를 사용하는 경우 @{...} 를 사용한다. 이것을 URL 링크 표현식이라 한다.
URL 링크 표현식을 사용하면 서블릿 컨텍스트를 자동으로 포함

속성 변경 - th:onclick
리터럴 대체 - |...|
|...| :이렇게 사용


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
<!DOCTYPE HTML>
<html  xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <link href="../css/bootstrap.min.css"
          th:href="@{/css/bootstrap.min.css}"
          rel="stylesheet">
</head>
<body>
<div class="container" style="max-width: 600px">
    <div class="py-5 text-center">
        <h2>상품 목록</h2>
    </div>
    <div class="row">
        <div class="col">
            <button class="btn btn-primary float-end"
                    onclick="location.href='addForm.html'"
                    th:onclick="|location.href='@{/basic/items/add}'|"
                    type="button">상품 등록</button>
        </div>
    </div>
    <hr class="my-4">
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>상품명</th>
                <th>가격</th>
                <th>수량</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="item : ${items}">
                <td><a href="item.html" th:href="@{/basic/items/{itemId}(itemId=${item.id})}" th:text="${item.id}">회원Id</a></td>
                <td><a href="item.html" th:href="@{|/basic/items/${item.id}|}" th:text="${item.itemName}">상품명</a></td>
                <td th:text="${item.price}">10000</td>
                <td th:text="${item.quantity}">10</td>
            </tr>
            </tbody>
        </table>
    </div>
</div> <!-- /container -->
</body>
</html>


// 상품등록 
