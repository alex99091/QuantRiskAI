const STRATEGIES = ['투자심리도', '이평선 교차', '볼린저 밴드', 'CCI', 'RSI'];

export default function StrategySidebar({ selected, onSelect }) {
  return (
    <div className="w-48 border-r p-2">
      <h2 className="font-bold mb-2">📌 매매전략</h2>
      {STRATEGIES.map(s => (
        <button
          key={s}
          onClick={() => onSelect(s)}
          className={`block w-full text-left px-2 py-1 rounded ${selected === s ? 'bg-blue-500 text-white' : 'hover:bg-gray-100'}`}
        >
          {s}
        </button>
      ))}
    </div>
  );
}
