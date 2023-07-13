<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar contatos</title>
<link rel="stylesheet" href="resources/style.css">
<link rel="stylesheet" href="resources/tableStyles.css">

</head>
<body>
	<div class="flex-container">
		<div class="content-container">
			<div class="form-container">
				<form action="listaContatos" method="post">
					<h1 style="font-size: 24px; text-align: center">
						Lista de contatos <br> <span style="color: green;">${msg}</span>
						<span style="color: red;">${error}</span>
					</h1>
					<br> <br> <span class="subtitle">NOME:</span> <br> <input
						type="text" required="required" name="nome"
						value="${contato.nome}"> <br> <span class="subtitle">EMAIL:</span>
					<br> <input type="email" name="email" required="required"
						value="${contato.email}"> <br> <br> <span
						class="subtitle">NUMERO:</span> <br> <input type="tel"
						required="required" name="numero" maxlength="11" value="${contato.numero}">
					<input type="hidden" name="id" value="${contato.id}"> <br>
					<input type="submit" value="ADICIONAR" class="submit-btn">
				</form>
			</div>
		</div>
	</div>

	<div class="datatable-container">
		<!-- ======= Header tools ======= -->
		<form action="listaContatos" method="get">
			<div class="header-tools">
				<div class="tools">
					<ul>
						<li>
							<button>
								<a style="text-decoration: none; color: white" href="listaContatos?acao=listar">Mostrar lista de contatos</a>
							</button>
						</li>
						<li>
							<button>
								<a >Total de contatos registrados: ${total}</a>
							</button>
						</li>
					</ul>
				</div>
				
			</form>
			<form id="form2" action="consultaContatos" method="post">
				<div class="search">
					<input style="width: 240px" type="search" name="consulta" class="search-input"
						onkeypress="return submitenter(this,event)"
						placeholder="Buscar..." />
				</div>
				<ul>
				
				</ul>
					<select
						style="padding: 7px; background-color: rgb(61, 68, 73); color: white; border: solid 1px rgb(255, 68, 73); border-radius: 3px; height: calc(1.5em + 0.75rem + 2px);"
						name="selecao">
						<option name="nome">Nome</option>
						<option name="email">Email</option>
						<option name="numero">Numero</option>
					</select>
				
				
			</form>
			
			<form id="form2" action="consultaContatos" method="post">
			</form>
		</div>

		<!-- ======= Table ======= -->
		<table class="datatable">
			<thead>
				<tr>
					<th>#</th>
					<th>Nome</th>
					<th>Email</th>
					<th>Numero</th>
					<th>Editar</th>
					<th>Excluir</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ctt}" var="contatos">
					<tr>
						<td><c:out value="${contatos.id}" /></td>
						<td><c:out value="${contatos.nome}" /></td>
						<td><c:out value="${contatos.email}" /></td>
						<td><c:out value="${contatos.numero}" /></td>
						<td><a
							href="listaContatos?acao=editar&contato=${contatos.id}"><img
								width="25px" height="25px" alt="Editar" title="Editar"
								src="resources/imgs/pen.png"></a></td>
						<td><a
							href="listaContatos?acao=excluir&contato=${contatos.id}"><img
								width="25px" height="25px" alt="Excluir" title="Excluir"
								src="resources/imgs/trash.png"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- ======= Footer tools ======= -->

	</div>
</body>
<script type="text/javascript">
	function submitenter(myfield, e) {
		var keycode;
		if (window.event)
			keycode = window.event.keyCode;
		else if (e)
			keycode = e.which;
		else
			return true;

		if (keycode == 13) {
			myfield.form.submit();
			return false;
		} else
			return true;
	}
</script>
</html>