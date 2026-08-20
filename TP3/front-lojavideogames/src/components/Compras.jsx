import { useState, useEffect } from "react";

function Compras() {
  const [compras, setCompras] = useState([]);
  const [usuarios, setUsuarios] = useState([]);
  const [jogos, setJogos] = useState([]);
  const [novaCompra, setNovaCompra] = useState({ usuarioId: "", jogosIds: [] });
  const [editando, setEditando] = useState(null);

  useEffect(() => {
    carregarCompras();
    fetch("http://localhost:8081/usuarios")
      .then(res => res.json())
      .then(setUsuarios);
    fetch("http://localhost:8082/jogos")
      .then(res => res.json())
      .then(setJogos);
  }, []);

  const carregarCompras = () => {
    fetch("http://localhost:8080/compras")
      .then((res) => res.json())
      .then((data) => setCompras(data));
  };

  const salvarCompra = (e) => {
    e.preventDefault();

    const compra = {
      usuario: { id: novaCompra.usuarioId },
      jogos: novaCompra.jogosIds.map(id => ({ id }))
    };

    const url = editando
      ? `http://localhost:8080/compras/${editando.id}`
      : "http://localhost:8080/compras";
    const method = editando ? "PUT" : "POST";

    fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(compra),
    })
      .then((res) => res.json())
      .then((compraSalva) => {
        if (editando) {
          setCompras(compras.map(c => c.id === compraSalva.id ? compraSalva : c));
        } else {
          setCompras([...compras, compraSalva]);
        }
        setNovaCompra({ usuarioId: "", jogosIds: [] });
        setEditando(null);
      });
  };

  const editarCompra = (compra) => {
    setNovaCompra({
      usuarioId: compra.usuario.id,
      jogosIds: compra.jogos.map(j => j.id),
    });
    setEditando(compra);
  };

  const deletarCompra = (id) => {
    if (!id) return;
    fetch(`http://localhost:8080/compras/${id}`, { method: "DELETE" })
      .then(() => carregarCompras());
  };

  return (
    <div>
      <h2>Compras</h2>
      <form onSubmit={salvarCompra} className="formulario">
        <select
          value={novaCompra.usuarioId}
          required
          onChange={(e) => setNovaCompra({ ...novaCompra, usuarioId: e.target.value })}
        >
          <option value="">Selecione Usuário</option>
          {usuarios.map(u => <option key={u.id} value={u.id}>{u.nome}</option>)}
        </select>

        <select
          multiple
          value={novaCompra.jogosIds}
          required
          onChange={(e) => {
            const values = Array.from(e.target.selectedOptions, opt => opt.value);
            setNovaCompra({ ...novaCompra, jogosIds: values });
          }}
        >
          {jogos.map(j => <option key={j.id} value={j.id}>{j.titulo}</option>)}
        </select>

        <button type="submit">{editando ? "Atualizar Compra" : "Cadastrar Compra"}</button>
      </form>

      <ul>
        {compras.map((compra) => (
          <li key={compra.id}>
            Compra #{compra.id} - Usuário: {compra.usuario?.nome}
            <ul>
              {compra.jogos?.map((jogo) => (
                <li key={jogo.id}>{jogo.titulo}</li>
              ))}
            </ul>
            <div>
              <button onClick={() => editarCompra(compra)}>Editar</button>
              <button onClick={() => deletarCompra(compra.id)}>Excluir</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Compras;
