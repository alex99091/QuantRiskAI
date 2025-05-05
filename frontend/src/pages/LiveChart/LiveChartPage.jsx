import { useState } from 'react';
import { SYMBOLS } from '@/models/symbols';
import { TIMEFRAMES } from '@/models/timeframe';
import TimeframeSelector from '@/components/chart/TimeframeSelector';
import PriceChart from '@/components/chart/PriceChart';
import SymbolSelector from '@/components/chart/SymbolSelector';
import StrategySidebar from "@/components/strategy/StrategySidebar";

export default function LiveChartPage() {
  const [selectedSymbol, setSelectedSymbol] = useState(SYMBOLS[0]);
  const [selectedTF, setSelectedTF] = useState(TIMEFRAMES[0]);
  const [selectedStrategy, setSelectedStrategy] = useState('투자심리도');
  const [price, setPrice] = useState(null);

  return (
    <div className="p-4 bg-white min-h-screen">
      <div className="text-center mb-4">
        <h1 className="text-2xl font-bold">📈 Bitget 실시간 차트</h1>
        <p className="text-sm">💰 현재 가격: {price ? `${price.toLocaleString()} USDT` : 'Loading...'}</p>
        <SymbolSelector selected={selectedSymbol} onSelect={setSelectedSymbol} />
      </div>

      <div className="flex">
        <StrategySidebar selected={selectedStrategy} onSelect={setSelectedStrategy} />

        <div className="flex-1">
          <TimeframeSelector selected={selectedTF.value} onSelect={setSelectedTF} />
          <PriceChart
            symbol={selectedSymbol.symbol}
            granularity={selectedTF.granularity}
            onPriceUpdate={setPrice}
          />
        </div>
      </div>
    </div>
  );
}