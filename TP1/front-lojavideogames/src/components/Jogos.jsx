import { useState, useEffect } from "react";

function Jogos() {
  const [jogos, setJogos] = useState([]);
  const [novoJogo, setNovoJogo] = useState({ titulo: "", plataforma: "", preco: 0 });
  const [editando, setEditando] = useState(null);
  const [loading, setLoading] = useState(false);
  const [erro, setErro] = useState(null);

  useEffect(() => {
    carregarJogos();
  }, []);

  const carregarJogos = async () => {
    try {
      setLoading(true);
      const res = await fetch("http://localhost:8080/jogos");
      if (!res.ok) throw new Error("Erro ao carregar jogos");
      const data = await res.json();
      setJogos(data);
    } catch (err) {
      setErro(err.message);
    } finally {
      setLoading(false);
    }
  };

  const salvarJogo = async (e) => {
    e.preventDefault();
    try {
      const jogoParaSalvar = { ...novoJogo, preco: Number(novoJogo.preco) };
      const url = editando
        ? `http://localhost:8080/jogos/${editando.id}`
        : "http://localhost:8080/jogos";
      const method = editando ? "PUT" : "POST";

      const res = await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(jogoParaSalvar),
      });

      if (!res.ok) throw new Error("Erro ao salvar jogo");
      const jogoSalvo = await res.json();

      setJogos((prev) =>
        editando ? prev.map((j) => (j.id === jogoSalvo.id ? jogoSalvo : j)) : [...prev, jogoSalvo]
      );

      setNovoJogo({ titulo: "", plataforma: "", preco: 0 });
      setEditando(null);
    } catch (err) {
      setErro(err.message);
    }
  };

  const editarJogo = (jogo) => {
    setNovoJogo({ titulo: jogo.titulo, plataforma: jogo.plataforma, preco: jogo.preco });
    setEditando(jogo);
  };

  const deletarJogo = async (id) => {
    try {
      const res = await fetch(`http://localhost:8080/jogos/${id}`, { method: "DELETE" });
      if (!res.ok) throw new Error("Erro ao excluir jogo");
      carregarJogos();
    } catch (err) {
      setErro(err.message);
    }
  };

  return (
    <div>
      <h2>Catálogo de Jogos</h2>

      {erro && <p style={{ color: "red" }}>{erro}</p>}
      {loading && <p>Carregando jogos...</p>}

      <form onSubmit={salvarJogo} className="formulario">
        <input
          type="text"
          placeholder="Título"
          value={novoJogo.titulo}
          required
          onChange={(e) => setNovoJogo({ ...novoJogo, titulo: e.target.value })}
        />
        <input
          type="text"
          placeholder="Plataforma"
          value={novoJogo.plataforma}
          required
          onChange={(e) => setNovoJogo({ ...novoJogo, plataforma: e.target.value })}
        />
        <input
          type="number"
          placeholder="Preço"
          value={novoJogo.preco}
          required
          onChange={(e) => setNovoJogo({ ...novoJogo, preco: Number(e.target.value) })}
        />
        <button type="submit">{editando ? "Atualizar" : "Cadastrar"}</button>
      </form>

      <ul>
        {jogos.map((jogo) => (
          <li key={jogo.id}>
            {jogo.titulo} ({jogo.plataforma}) - R${jogo.preco.toFixed(2)}
            <div>
              <button onClick={() => editarJogo(jogo)}>Editar</button>
              <button onClick={() => deletarJogo(jogo.id)}>Excluir</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Jogos;
