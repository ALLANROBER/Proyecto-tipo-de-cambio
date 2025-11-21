export default function Table({ registros }: { registros: any[] }) {
  return (
    <table className="historial-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Fecha Consulta</th>
          <th>Fecha API</th>
          <th>Tipo Cambio</th>
          <th>Origen</th>
        </tr>
      </thead>

      <tbody>
        {registros.map((r) => (
          <tr key={r.id}>
            <td>{r.id}</td>
            <td>{r.fechaConsulta}</td>
            <td>{r.fechaTipoCambio}</td>
            <td>{r.tipoCambio}</td>
            <td>{r.origenApi}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
