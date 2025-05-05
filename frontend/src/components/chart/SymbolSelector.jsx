import { SYMBOLS } from '@/models/symbols';

export default function SymbolSelector({ selected, onSelect }) {
  return (
    <div className="flex gap-2 justify-center mb-2">
      {SYMBOLS.map(s => (
        <button
          key={s.symbol}
          onClick={() => onSelect(s)}
          className={`px-3 py-1 rounded border ${selected.symbol === s.symbol ? 'bg-blue-500 text-white' : 'bg-white'}`}
        >
          {s.name}
        </button>
      ))}
    </div>
  );
}
