import React from "react";
import { Link } from "react-router-dom";
import { Panel } from "muicss/react";
import "../../index.css";
import { Button, TextField, Typography } from "@material-ui/core";

class CadastroUsuario extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            username: "",
            email: "",
            password: "",
            error: ""
        };
    }

    handleCadastro = e => {
        e.preventDefault();
        //implementar cadastro
    };


    render() {

        return (
            <div className="background">
                <Panel className="estilo_panel">
                    <form onSubmit={this.handleCadastro} className="estilo_form_padrao">
                        {this.state.error && <p className="error">{this.state.error}</p>}
                        <Typography
                            className="titulo"
                            variant="h3"
                        >
                            Cadastro
                    </Typography>
                        <TextField
                            className="textField"
                            type="text"
                            label="Login"
                            name="username"
                            variant="outlined"
                            onChange={e => this.setState({ username: e.target.value })}
                        />
                        <p />
                        <TextField
                            className="textField"
                            type="password"
                            label="Senha"
                            name="password"
                            variant="outlined"
                            onChange={e => this.setState({ password: e.target.value })}
                        />
                        <p />
                        <Button type="submit" variant="contained" color="secondary">Cadastrar</Button>
                        <p />
                    Já tem cadastro? Faça seu <Link to="/login">Login</Link>
                    </form>
                </Panel>
            </div>

        );

    }
}


export default CadastroUsuario;