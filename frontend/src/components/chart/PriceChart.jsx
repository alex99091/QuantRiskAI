import { useEffect, useRef } from 'react';
import { createChart } from 'lightweight-charts';

export default function PriceChart({ granularity }) {
  const chartContainerRef = useRef(null);
  const chartRef = useRef(null);
  const seriesRef = useRef(null);

  useEffect(() => {
    if (!granularity) return;
  
    const chart = createChart(chartContainerRef.current, {
      width: chartContainerRef.current.clientWidth,
      height: chartContainerRef.current.clientHeight,
      layout: { background: { color: '#fff' }, textColor: '#000' },
      grid: { vertLines: { color: '#eee' }, horzLines: { color: '#eee' } },
      timeScale: { timeVisible: true, secondsVisible: true },
    });
  
    const series = chart.addLineSeries();
    chartRef.current = chart;
    seriesRef.current = series;
  
    const fetchCandleData = async () => {
      try {
        const res = await fetch('https://api.bitget.com/api/mix/v1/market/history-candles', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            symbol: 'BTCUSDT_UMCBL',
            granularity: granularity.toString(),
          }),
        });
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
  
        const json = await res.json();
        if (!json?.data) throw new Error('Empty data');
  
        const lineData = json.data.reverse().map(c => ({
          time: Math.floor(Number(c[0]) / 1000),
          value: parseFloat(c[4]),
        }));
        series.setData(lineData);
      } catch (err) {
        console.error('🔥 Fetch error:', err.message);
        series.setData([]);
      }
    };
  
    fetchCandleData(); // 👈 async 함수 호출
  
    // 실시간 WebSocket 연결
    const ws = new WebSocket('wss://ws.bitget.com/mix/v1/stream');
    ws.onopen = () => {
      ws.send(JSON.stringify({
        op: 'subscribe',
        args: [{
          instType: 'mc',
          channel: 'ticker',
          instId: 'BTCUSDT_UMCBL',
        }],
      }));
    };
  
    ws.onmessage = (event) => {
      const json = JSON.parse(event.data);
      if (json?.data && json?.arg?.channel === 'ticker') {
        const price = parseFloat(json.data[0].last);
        const now = Math.floor(Date.now() / 1000);
        series.update({ time: now, value: price });
      }
    };
  
    return () => {
      ws.close();
      chart.remove();
    };
  }, [granularity]);  

  return <div ref={chartContainerRef} className="w-full h-[500px] border rounded" />;
}
