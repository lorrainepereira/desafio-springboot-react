export const getCep  = async(cep) => {
  const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`)
  return response.json();
      
}

export const login  = async(dadosLogin) => {
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: dadosLogin
  };
  const response = await fetch(`http://localhost:8080/api/usuario/login`, requestOptions)
  const data = await response.json();
  return data;
}


export const getClientes  = async() => {
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
  };
  const response = await fetch(`http://localhost:8080/api/cliente/`, requestOptions)
  const data = await response.json();
  return data;
}

export const salvarCliente  = async(dadosCliente) => {
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: dadosCliente
  };
  const response = await fetch(`http://localhost:8080/api/cliente/salvar`, requestOptions)
  const data = await response.json();
  return data;
}

export const deletarCliente  = (idCliente) => {
  const requestOptions = {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
  };
  fetch(`http://localhost:8080/api/cliente/apagar/`+idCliente, requestOptions)
}
