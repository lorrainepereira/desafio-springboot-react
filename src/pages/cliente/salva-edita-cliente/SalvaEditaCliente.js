import React from "react";
import { Panel } from "muicss/react";
import './SalvaEditaCliente.css';
import { Button, TextField, Grid, MenuItem} from "@material-ui/core";
import { cepMask, cpfMask, removeMask, validateEmail } from "../../../util/mask";
import { siglasEstados } from "../../../enums/EnumEstado";
import { tiposTelefones } from "../../../enums/EnumTipoTelefone";

import { getCep, salvarCliente } from "../../../services/api";
import "../../../index.css";


class SalvaEditaCliente extends React.Component{

    constructor(props){
        super(props);


        this.state = {
            nome: "",
            email: "",
            cpf: "",
            cep: "",
            logradouro: "",
            bairro: "",
            cidade: "",
            uf: {},
            complemento: "",
            tipo: {},
            numero: "",
            error: ""
        };
    }
    
    handleCadastro = async() => {
        let endereco = {cep: +removeMask(this.state.cep), logradouro: this.state.logradouro, bairro: this.state.bairro, 
            cidade: this.state.cidade, uf: this.state.uf, complemento: this.state.complemento};

        let telefones = [];
        telefones.push({tipo: this.state.tipo, numero: this.state.numero});   

        let cliente = JSON.stringify({nome: this.state.nome, cpf: removeMask(this.state.cpf), email: this.state.email, 
            endereco: endereco, telefones: telefones});

        let resposta = await salvarCliente(cliente);
    
        if (resposta != null) {
            this.props.history.push({
                pathname: `/listagem-cliente`,
                state: this.state
            });
        }
    }

    buscaCep = async () =>{
        let cep = await getCep(removeMask(this.state.cep));
        if(cep == null){
            this.setState( {error: "Erro ao consultar o CEP."});
        }else{
            this.setState( {error: ""});
            this.setState( {logradouro: cep.logradouro});
            this.setState( {bairro: cep.bairro});
            this.setState( {uf: cep.uf});
            this.setState( {cidade: cep.localidade});
            this.setState( {complemento: cep.complemento});
        }  
         
    }

    validaEmail = email => {
       this.setState({ email: email })

       let isValidoEmail = validateEmail(this.state.email);

       if(!isValidoEmail)
          this.setState( {error: "Email Inválido."});
       else 
          this.setState( {error: ""});

    }
    


    render (){ 
        return (
        <div className="background">
            <Panel className="estilo_panel">
                <form onSubmit={this.handleCadastro} className="estilo_form">
                    {this.state.error && <p className="error">{this.state.error}</p>}
                    <Grid container spacing={3}>
                        <Grid item xs={12}>
                            <TextField
                                className="textField"
                                type="text"
                                label="Nome"
                                name="nome"
                                variant="outlined"
                                required
                                inputProps={{
                                    minLength: 3,
                                    maxLength:100
                                }}  
                                value={this.state.nome}
                                fullWidth
                                onChange={e => this.setState({ nome: e.target.value })}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                className="textField"
                                type="text"
                                label="CPF"
                                name="cpf"
                                variant="outlined"
                                required
                                value={this.state.cpf}
                                onChange={e => this.setState({ cpf: cpfMask(e.target.value) })}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                className="textField"
                                type="text"
                                label="CEP"
                                name="cep"
                                variant="outlined"
                                required
                                value={this.state.cep}
                                onChange={e => this.setState({ cep: cepMask(e.target.value) })}
                            />
                        </Grid>
                        <Grid item xs={12} sm={3}>
                            <Button id="buscaCep" variant="contained" color="primary" onClick={this.buscaCep}>Busca CEP</Button>
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                className="textField"
                                type="text"
                                label="Logradouro"
                                name="logradouro"
                                variant="outlined"
                                required
                                value={this.state.logradouro}
                                fullWidth
                                onChange={e => this.setState({ logradouro: e.target.value })}
                            />
                        </Grid>
                        <Grid item xs={12} sm={3}>
                            <TextField
                                className="textField"
                                type="text"
                                label="Bairro"
                                name="bairro"
                                variant="outlined"
                                required
                                value={this.state.bairro}
                                onChange={e => this.setState({ bairro: e.target.value })}
                            />
                        </Grid>
                        <Grid item xs={12} sm={3}>
                            <TextField
                                className="textField"
                                type="text"
                                label="Cidade"
                                name="cidade"
                                variant="outlined"
                                required
                                value={this.state.cidade}
                                onChange={e => this.setState({ cidade: e.target.value })}
                            />
                        </Grid>
                        <Grid item xs={12} sm={3}>
                            <TextField
                                select
                                className="textField"
                                label="UF"
                                name="uf"
                                variant="outlined"
                                required
                                value={this.state.uf}
                                onChange={e => this.setState({ uf: e.target.value })}
                            >
                            {siglasEstados().map((option) => (
                                <MenuItem key={option} value={option}>
                                    {option}
                                </MenuItem>
                            ))}
                            </TextField>
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                className="textField"
                                type="text"
                                label="Complemento"
                                name="complemento"
                                variant="outlined"
                                value={this.state.complemento}
                                fullWidth
                                onChange={e => this.setState({ complemento: e.target.value })}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                className="textField"
                                type="text"
                                label="E-mail"
                                name="email"
                                variant="outlined"
                                value={this.state.email}
                                fullWidth
                                onChange={e => this.validaEmail(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                className="textField"
                                select
                                label="Tipo de Telefone"
                                name="tipo"
                                id="tipo"
                                variant="outlined"
                                value={this.state.tipo}
                                onChange={e => this.setState({ tipo: e.target.value })}
                            >
                                {tiposTelefones().map((option) => (
                                <MenuItem key={option} value={option}>
                                    {option}
                                </MenuItem>
                                ))}
                            </TextField>    
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                className="textField"
                                type="text"
                                label="Número de Telefone"
                                name="numero"
                                variant="outlined"
                                value={this.state.numero}
                                onChange={e => this.setState({ numero: e.target.value })}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <Button type="submit" variant="contained" color="primary" className="botaoSalvar">Salvar</Button>
                        </Grid>
                    </Grid>
                </form>
            </Panel>
        </div>
        );
    }
}


export default SalvaEditaCliente;