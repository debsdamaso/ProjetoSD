# API de Medicamentos

API do Projeto API de Medicamentos - Gestão de Medicamentos

Obs: A documentação ainda está em desenvolvimento, posteriomente serão adicionados outras categorias.

## Requisitos

- [ ] CRUD de Medicamentos
- [ ] Autenticação
- [ ] Dashboard de Medicamentos

## Documentação da API

### Endpoints 

- [Listar Medicamentos](#listar-medicamentos)
- [Cadastrar Medicamento](#cadastrar-medicamento)
- [Detalhar Medicamento](#detalhar-medicamento)
- [Apagar Medicamento](#apagar-medicamento)
- [Atualizar Medicamento](#atualizar-medicamento)

### Listar Medicamentos

`GET` /api/medicamentos

Retorna um array com os medicamentos cadastrados.

#### Exemplo de Resposta

```js
[ 
    { "id": 1, 
    "nome": "Buscopan", 
    "fabricante": "Medley", 
    "quantidade": 100 
    } 
]
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Lista de medicamentos retornada com sucesso
|401| Não autenticado. Se autentique em /login

---

### Cadastrar Medicamento

`POST` /api/medicamentos

Cadastra um medicamento com os dados enviados no corpo da requisição.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|nome|string|✅|Um nome curto para o medicamento
|fabricante|string|✅|O nome do fabricante
|quantidade|integer|✅|A quantidade disponível do medicamento

#### Exemplo de Requisição

json 
// POST /api/medicamentos 
    { 
        "nome": "Buscopan", 
        "fabricante": "Medley", 
        "quantidade": 100 
    }

#### Exemplo de Resposta

```js
[ 
    { 
        "id": 1, 
        "nome": "Buscopan", 
        "fabricante": "Medley", 
        "quantidade": 100 
    } 
]
```

#### Códigos de Status

|código|descrição
|------|---------
|201| Medicamento criado com sucesso
|400| Validação falhou. Verifique o corpo da requisição
|401| Não autenticado. Se autentique em /login

---

### Detalhar Medicamento

`GET` /api/medicamentos/`{id}`

Retorna os detalhes do medicamento com o `id` informado no path.

#### Exemplo de Resposta

json 
// GET /api/medicamentos/1 
    { 
        "id": 1, "nome": "Buscopan", 
        "fabricante": "Medley", 
        "quantidade": 100 
    }


#### Códigos de Status

|código|descrição
|------|---------
|200| Medicamento retornado com sucesso
|401| Não autenticado. Se autentique em /login
|404| Não existe medicamento com o `id` informado
---

### Apagar Medicamento

`DELETE` /api/medicamentos/`{id}`

Apaga o medicamento com o `id` informado no path.

#### Códigos de Status

|código|descrição
|------|---------
|204| Medicamento apagado com sucesso
|401| Não autenticado. Se autentique em /login
|404| Não existe medicamento com o `id` informado
---

### Atualizar Medicamento

`PUT` /api/medicamentos/`{id}`

Atualiza os dados do medicamento com o `id` informado no path, utilizando as informações do corpo da requisição.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|nome|string|✅|O novo nome curto para o medicamento
|fabricante|string|✅|O novo nome do fabricante
|quantidade|integer|✅|A nova quantidade disponível do medicamento

#### Exemplo de Requisição

// PUT /api/medicamentos/1 
    { 
        "nome": "Buscopan Composto", 
        "fabricante": "Medley", 
        "quantidade": 120 
    }

#### Exemplo de Resposta

```js
[ 
    { 
        "id": 1, 
        "nome": "Buscopan Composto", 
        "fabricante": "Medley", 
        "quantidade": 100 
    } 
]
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Medicamento atualizado com sucesso
|400| Validação falhou. Verifique o corpo da requisição
|401| Não autenticado. Se autentique em /login
|404| Não existe medicamento com o `id` informado