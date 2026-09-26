import { useState, useEffect } from "react";
import Jogos from "./components/Jogos";
import Usuarios from "./components/Usuarios";
import Compras from "./components/Compras";
import "./App.css";

function App() {
  const [tela, setTela] = useState("jogos");
  const [temaEscuro, setTemaEscuro] = useState(() => {
    const saved = localStorage.getItem("temaEscuro");
    return saved === "true";
  });

  useEffect(() => {
    document.body.className = temaEscuro ? "dark" : "light";
    localStorage.setItem("temaEscuro", temaEscuro);
  }, [temaEscuro]);

  const alternarTema = () => {
    setTemaEscuro(!temaEscuro);
  };

  return (
    <div className="container">
      <h1>Loja de Videogames</h1>
      <nav className="menu">
        <button
          className={tela === "jogos" ? "active" : ""}
          onClick={() => setTela("jogos")}
        >
          🎮 Jogos
        </button>
        <button
          className={tela === "usuarios" ? "active" : ""}
          onClick={() => setTela("usuarios")}
        >
          👤 Usuários
        </button>
        <button
          className={tela === "compras" ? "active" : ""}
          onClick={() => setTela("compras")}
        >
          🛒 Compras
        </button>
        <button onClick={alternarTema}>
          {temaEscuro ? "🌞 Claro" : "🌙 Escuro"}
        </button>
      </nav>

      <div className="conteudo">
        {tela === "jogos" && <Jogos />}
        {tela === "usuarios" && <Usuarios />}
        {tela === "compras" && <Compras />}
      </div>
    </div>
  );
}

export default App;
