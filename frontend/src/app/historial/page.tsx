"use client";

import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import Table from "../components/Table";
import { obtenerHistorial } from "../services/api";

export default function Historial() {
  const [registros, setRegistros] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);
  const [toast, setToast] = useState<string | null>(null);

  useEffect(() => {
    obtenerHistorial()
      .then((res) => {
        if (!res.success) {
          setToast(res.message);   
          return;
        }

        setRegistros(res.data ?? []);
      })
      .catch(() => {
        setToast("Error cargando historial");
      })
      .finally(() => {
        setLoading(false);

    
        setTimeout(() => setToast(null), 2000);
      });
  }, []);

  return (
    <main style={{ padding: "40px" }}>
      <Navbar />

      <h2>Historial</h2>

      {toast && <div className="toast-error">{toast}</div>}

      {loading ? <p>Cargando historial...</p> : <Table registros={registros} />}
    </main>
  );
}
