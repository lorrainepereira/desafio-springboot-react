import React from "react";
import { Paper, TableContainer, Table, TableHead, TableRow, TableCell, TableBody, TablePagination, Typography, IconButton } from "@material-ui/core";
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import AddBoxIcon from '@material-ui/icons/AddBox';
import "./ListagemCliente.css";
import { cpfMask } from "../../../util/mask";
import { getClientes } from "../../../services/api";


const columns = [
    { id: 'nome', label: 'Nome', minWidth: 170 },
    { id: 'email', label: 'Email', minWidth: 170 },
    {
        id: 'cpf',
        label: 'CPF',
        align: 'right',
        format: (value) => cpfMask(value),
    },
    {
        id: 'alterar',
        label: ' ',
        minWidth: 50,
        align: 'center',
    },
    {
        id: 'delete',
        label: ' ',
        minWidth: 50,
        align: 'center',
    }
];



class ListagemCliente extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            rowsPerPage: 5,
            rows: []
        }

        this.clientes();
    }

    handleChangePage = (event, newPage) => {
        this.setState({ page: newPage });
    }

    clientes = async () => {
        let resposta = await getClientes();
        this.setState({ rows: resposta })
        console.log(this.state.rows);

    }

    deletarCliente = (id) => {
     //  this.deletarCliente(id);
    }

    handleChangeRowsPerPage = (event) => {
        this.setState({ rowsPerPage: event.target.value });
        this.setState({ page: 0 });
    }

    goPageCadastroCliente = () => {
        this.props.history.push({
            pathname: `/salva-cliente`,
            state: this.state
        });

    }


    render() {
        if (this.state.rows !== undefined) {

            return (
                <div className="background">
                    <Paper>
                        <div className="borda">
                            <Typography
                                className="titulo"
                                variant="h3">
                                Clientes
                        </Typography>
                            <TableContainer className="container">
                                <IconButton aria-label="add" onClick={this.goPageCadastroCliente}>
                                    <AddBoxIcon fontSize="default" />
                                </IconButton>

                                <Table stickyHeader aria-label="sticky table">
                                    <TableHead>
                                        <TableRow className="borda">
                                            {columns.map((column) => (
                                                <TableCell
                                                    key={column.id}
                                                    align={column.align}
                                                    style={{ minWidth: column.minWidth }}
                                                >
                                                    {column.label}
                                                </TableCell>
                                            ))}
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {this.state.rows.slice(this.state.page * this.state.rowsPerPage, this.state.page * this.state.rowsPerPage + this.state.rowsPerPage).map((row) => {
                                            return (
                                                <TableRow hover role="checkbox" tabIndex={-1} key={row.idCliente}>
                                                    {columns.map((column) => {
                                                        const value = row[column.id];
                                                        return (
                                                            <TableCell key={column.id} align={column.align} >
                                                                <IconButton aria-label="edit" style={{ display: column.id === 'alterar' ? 'block' : 'none' }}>
                                                                    <EditIcon fontSize="small" />
                                                                </IconButton>
                                                                <div onClick={this.deletarCliente()}>
                                                                <IconButton aria-label="delete" style={{ display: column.id === 'delete' ? 'block' : 'none' }}>
                                                                    <DeleteIcon fontSize="small" />
                                                                </IconButton>
                                                                </div>
                                                                {column.format && typeof value === 'number' && column.id !== 'delete' && column.id !== 'alterar' ? column.format(value) : value}
                                                            </TableCell>
                                                        );
                                                    })}
                                                </TableRow>
                                            );
                                        })}
                                    </TableBody>
                                </Table>
                            </TableContainer>
                            <TablePagination
                                rowsPerPageOptions={[5, 10, 25, 100]}
                                component="div"
                                count={this.state.rows.length}
                                rowsPerPage={this.state.rowsPerPage}
                                page={this.state.page}
                                onChangePage={this.handleChangePage}
                                onChangeRowsPerPage={this.handleChangeRowsPerPage}
                            />
                        </div>
                    </Paper>
                </div>
            );
        }
    }
}


export default ListagemCliente;
