export default function ExchangeCard({ data }: { data: any }) {
  return (
    <div className="exchange-card">
      <p><strong>Fecha API:</strong> {data.fechaTipoCambio}</p>
      <p><strong>Tipo de Cambio:</strong> {data.tipoCambio}</p>
    </div>
  );
}
