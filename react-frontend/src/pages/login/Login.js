import React from "react";
import { Link} from "react-router-dom";
import { Panel } from "muicss/react";
import "../../index.css";
import { Button, TextField, Typography } from "@material-ui/core";
import { login } from "../../services/api";


class Login extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            error: ""
        };
    }

    handleLogin = async(e) => {
        e.preventDefault();
        let json = JSON.stringify({ login: this.state.username, senha: this.state.password });
        let resposta = await login(json);
        
        if (resposta.login != null) {
            this.props.history.push({
                pathname: `/listagem-cliente`,
                state: this.state
            });
        }
    }


    render() {

        return (
            <div className="background">
                <Panel className="estilo_panel">
                    <form onSubmit={this.handleLogin} className="estilo_form_padrao">
                        {this.state.error && <p className="error">{this.Componentstate.error}</p>}
                        <Typography
                            className="titulo"
                            variant="h3"
                        >
                            Login
                    </Typography>
                        <TextField
                            className="textField"
                            type="text"
                            label="Login"
                            name="username"
                            variant="outlined"
                            value={this.state.username}
                            required
                            onChange={e => this.setState({ username: e.target.value })}
                        />
                        <p />
                        <TextField
                            className="textField"
                            type="password"
                            label="Senha"
                            name="password"
                            variant="outlined"
                            value={this.state.password}
                            required
                            onChange={e => this.setState({ password: e.target.value })}
                        />
                        <p />
                        <Button type="submit" variant="contained" color="primary">Login</Button>
                        <p />
                    Não tem cadastro? Faça seu <Link to="/cadastrar">Cadastro</Link>
                    </form>
                </Panel>
            </div >

        );
    }
}


export default Login;