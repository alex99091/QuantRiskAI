import { TIMEFRAMES } from '../../models/timeframe';

export default function TimeframeSelector({ selected, onSelect }) {
  return (
    <div className="flex gap-2 mb-4">
      {TIMEFRAMES.map(tf => (
        <button
          key={tf.value}
          className={`px-3 py-1 rounded border text-sm ${
            tf.value === selected ? 'bg-blue-600 text-white' : 'bg-white'
          }`}
          onClick={() => onSelect(tf)}
        >
          {tf.label}
        </button>
      ))}
    </div>
  );
}
