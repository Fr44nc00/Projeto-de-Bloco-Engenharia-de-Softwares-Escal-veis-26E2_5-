import { useState, useEffect } from "react";

function Usuarios() {
  const [usuarios, setUsuarios] = useState([]);
  const [novoUsuario, setNovoUsuario] = useState({ nome: "", email: "", tipo: "", senha: "" });
  const [editando, setEditando] = useState(null);

  useEffect(() => {
    carregarUsuarios();
  }, []);

  const carregarUsuarios = () => {
    fetch("http://localhost:8080/usuarios")
      .then((res) => res.json())
      .then((data) => setUsuarios(data));
  };

  const salvarUsuario = (e) => {
    e.preventDefault();
    if (editando) {
      fetch(`http://localhost:8080/usuarios/${editando.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(novoUsuario),
      })
        .then((res) => res.json())
        .then((usuarioAtualizado) => {
          setUsuarios(usuarios.map(u => u.id === usuarioAtualizado.id ? usuarioAtualizado : u));
          setNovoUsuario({ nome: "", email: "", tipo: "", senha: "" });
          setEditando(null);
        });
    } else {
      fetch("http://localhost:8080/usuarios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(novoUsuario),
      })
        .then((res) => res.json())
        .then((usuarioSalvo) => {
          setUsuarios([...usuarios, usuarioSalvo]);
          setNovoUsuario({ nome: "", email: "", tipo: "", senha: "" });
        });
    }
  };

  const editarUsuario = (usuario) => {
    setNovoUsuario(usuario);
    setEditando(usuario);
  };

  const deletarUsuario = (id) => {
    if (!id) return;
    fetch(`http://localhost:8080/usuarios/${id}`, { method: "DELETE" })
      .then(() => carregarUsuarios());
  };

  return (
    <div>
      <h2>Usuários</h2>
      <form onSubmit={salvarUsuario} className="formulario">
        <input
          type="text"
          placeholder="Nome"
          value={novoUsuario.nome}
          required
          onChange={(e) => setNovoUsuario({ ...novoUsuario, nome: e.target.value })}
        />
        <input
          type="email"
          placeholder="Email"
          value={novoUsuario.email}
          required
          onChange={(e) => setNovoUsuario({ ...novoUsuario, email: e.target.value })}
        />
        <input
          type="text"
          placeholder="Tipo (CLIENTE/FUNCIONARIO)"
          value={novoUsuario.tipo}
          required
          onChange={(e) => setNovoUsuario({ ...novoUsuario, tipo: e.target.value })}
        />
        <input
          type="password"
          placeholder="Senha"
          value={novoUsuario.senha}
          required
          onChange={(e) => setNovoUsuario({ ...novoUsuario, senha: e.target.value })}
        />
        <button type="submit">{editando ? "Atualizar" : "Cadastrar"}</button>
      </form>

      <ul>
        {usuarios.map((usuario) => (
          <li key={usuario.id}>
            {usuario.nome} ({usuario.tipo}) - {usuario.email}
            <div>
              <button onClick={() => editarUsuario(usuario)}>Editar</button>
              <button onClick={() => deletarUsuario(usuario.id)}>Excluir</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Usuarios;
