"use client";

import { useState } from "react";
import Navbar from "./components/Navbar";
import ExchangeCard from "./components/ExchangeCard";
import { consultarTipoCambio } from "./services/api";

export default function Home() {
  const [data, setData] = useState<any>(null);
  const [loading, setLoading] = useState(false);
  const [toast, setToast] = useState<string | null>(null);

  const handleConsultar = async () => {
    setToast(null); 

    try {
      setLoading(true);
      const respuesta = await consultarTipoCambio();

      if (!respuesta.success) {
        setToast(respuesta.message);    
        return;
      }

      setData(respuesta.data);
    } catch {
      setToast("Error consultando API"); 
    } finally {
      setLoading(false);

      setTimeout(() => setToast(null), 2000);
    }
  };

  return (
    <>
      <Navbar />

      <main className="container">
        <div className="card">
          <h1 className="title">Tipo de Cambio — Banco de Guatemala</h1>

          {toast && <div className="toast-error">{toast}</div>}

          <button className="btn" onClick={handleConsultar}>
            {loading ? "Consultando..." : "Consultar Tipo de Cambio"}
          </button>

          {data && <ExchangeCard data={data} />}
        </div>
      </main>
    </>
  );
}
