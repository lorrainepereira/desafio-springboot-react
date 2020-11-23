import React from "react";
import { BrowserRouter, Route, Switch, Redirect} from "react-router-dom";
import Cadastro from "./pages/cadastrar-usuario/CadastroUsuario";
import Login from "./pages/login/Login";
import ListagemCliente from "./pages/cliente/listagem-cliente/ListagemCliente";
import SalvaEditaCliente from "./pages/cliente/salva-edita-cliente/SalvaEditaCliente";

const Routes = () => (

  <BrowserRouter>
    <Switch>
      <Redirect exact from="/" to="/login"/>

      <Route component={Login} path="/login"/>
      <Route component={Cadastro} path="/cadastrar"/>
      <Route component={ListagemCliente} path="/listagem-cliente"/>
      <Route component={SalvaEditaCliente} path="/salva-cliente"/>
      <Route path="*" component={() => <h1>Página não encontrada.</h1>} />
    </Switch>
  </BrowserRouter>
  

);

export default Routes;