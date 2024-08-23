<jsp:include page="layout/adminHead.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div id="wrapper">
  <jsp:include page="component/admin/sideBar.jsp"></jsp:include>
  <div id="content-wrapper" class="d-flex flex-column">

    <div id="content">
      <jsp:include page="component/admin/navbar.jsp"></jsp:include>

      <div style="margin-left: 35px">
        <h5 style="margin-bottom: 15px">Add Section</h5>
        <form:form modelAttribute="section" action="/admin/section/${section.id}" method="post">
          <form:hidden path="id" />
          <label for="name" class="d-block mb-1">Section Name</label>
          <form:input path="name" type="text" name="name" id="name" style="border: 1px solid #bfbfbf; border-radius: 5px;" />
          <p class="error-msg">${error}</p>
          <button type="submit" style="background: #3158C9; color: #FFF; border: 0; font-size: 15px; padding: 5px 10px; border-radius: 5px;">Submit</button>
        </form:form>
      </div>

    </div>

  </div>

</div>
<jsp:include page="layout/adminFoot.jsp"></jsp:include>
