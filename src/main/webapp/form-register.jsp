<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Gerência de Configuração</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
	<form action="register" method="post">
		<h1 class="h3 mb-3 fw-normal">Cadastrar novo usuário:</h1>

		<!-- Nome -->
		<div class="form-floating mb-3">
			<input type="text" name="name" class="form-control" id="floatingName" placeholder="Digite seu nome." required />
			<label for="floatingName">Nome</label>
		</div>

		<!-- E-mail -->
		<div class="form-floating mb-3">
			<input type="email" name="email" class="form-control" id="floatingEmail" placeholder="seu-email@email.com" required />
			<label for="floatingEmail">E-mail</label>
		</div>

		<!-- Senha -->
		<div class="form-floating mb-3">
			<input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Digite sua senha." required />
			<label for="floatingPassword">Senha</label>
		</div>

		<!-- Idade -->
		<div class="form-floating mb-3">
			<input type="number" name="idade" class="form-control" id="floatingIdade" placeholder="Digite sua idade." required />
			<label for="floatingIdade">Idade</label>
		</div>

		<!-- Status -->
		<div class="form-floating mb-3">
			<select name="status" class="form-select" id="floatingStatus" required>
				<option value="">Selecione o status</option>
				<option value="true">Ativo</option>
				<option value="false">Inativo</option>
			</select>
			<label for="floatingStatus">Status</label>
		</div>

		<!-- Botão -->
		<button type="submit" class="btn btn-primary w-100 py-2 mt-2">Cadastrar-se</button>
	</form>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
