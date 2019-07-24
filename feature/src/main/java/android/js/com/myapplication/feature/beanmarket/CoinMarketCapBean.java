package android.js.com.myapplication.feature.beanmarket;

import java.util.List;

/**
 * Created by root on 2019/6/27.
 */

public class CoinMarketCapBean {


    /**
     * total : 2250
     * offset : 0
     * pageTotal : 23
     * pageNo : 1
     * dataList : [{"priceChange":7.3,"symbol":"BTC","marketCap":2.28734228826E11,"dexId":0,"rankMarketCap":1,"currentPrice":12862.69,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":46365352774,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BTC Bitcoin","currencyPriceId":499364,"currencyId":408,"circulatingVolume":17782762},{"priceChange":4.6,"symbol":"ETH","marketCap":3.5842370801E10,"dexId":0,"rankMarketCap":2,"currentPrice":336.05,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":16843218322,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ETH Ethereum","currencyPriceId":499365,"currencyId":409,"circulatingVolume":106657498},{"priceChange":-2.6,"symbol":"XRP","marketCap":1.9618477345E10,"dexId":0,"rankMarketCap":3,"currentPrice":0.460889,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":3570650662,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"XRP XRP","currencyPriceId":499366,"currencyId":410,"circulatingVolume":42566596173},{"priceChange":-0.1,"symbol":"BCH","marketCap":8.590855699E9,"dexId":0,"rankMarketCap":4,"currentPrice":481.02,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":3598825806,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BCH Bitcoin Cash","currencyPriceId":499367,"currencyId":412,"circulatingVolume":17859825},{"priceChange":-4.2,"symbol":"LTC","marketCap":8.106241114E9,"dexId":0,"rankMarketCap":5,"currentPrice":129.89,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":5665236620,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"LTC Litecoin","currencyPriceId":499368,"currencyId":411,"circulatingVolume":62407501},{"priceChange":-7.2,"symbol":"EOS","marketCap":6.180722618E9,"dexId":0,"rankMarketCap":6,"currentPrice":6.71,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":5050526320,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"EOS EOS","currencyPriceId":499369,"currencyId":413,"circulatingVolume":920794151},{"priceChange":-3.3,"symbol":"BNB","marketCap":4.949174032E9,"dexId":0,"rankMarketCap":7,"currentPrice":35.06,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":459234608,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BNB Binance Coin","currencyPriceId":499370,"currencyId":414,"circulatingVolume":141175490},{"priceChange":-8.4,"symbol":"BSV","marketCap":3.888150864E9,"dexId":0,"rankMarketCap":8,"currentPrice":217.76,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":988937279,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BSV Bitcoin SV","currencyPriceId":499371,"currencyId":415,"circulatingVolume":17854986},{"priceChange":0.1,"symbol":"USDT","marketCap":3.571999028E9,"dexId":0,"rankMarketCap":9,"currentPrice":0.999176,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":42881522942,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"USDT Tether","currencyPriceId":499372,"currencyId":416,"circulatingVolume":3574945622},{"priceChange":-1.7,"symbol":"ADA","marketCap":2.498079465E9,"dexId":0,"rankMarketCap":10,"currentPrice":0.09635,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":418361148,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ADA Cardano","currencyPriceId":499373,"currencyId":418,"circulatingVolume":25927070538},{"priceChange":-5.8,"symbol":"TRX","marketCap":2.422419586E9,"dexId":0,"rankMarketCap":11,"currentPrice":0.036328,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":1281506474,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"TRX TRON","currencyPriceId":499374,"currencyId":419,"circulatingVolume":66682072191},{"priceChange":-3.4,"symbol":"XLM","marketCap":2.33789008E9,"dexId":0,"rankMarketCap":12,"currentPrice":0.120419,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":692962308,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"XLM Stellar","currencyPriceId":499375,"currencyId":417,"circulatingVolume":19414689403},{"priceChange":-1.1,"symbol":"LEO","marketCap":1.923075586E9,"dexId":0,"rankMarketCap":13,"currentPrice":1.92,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":16645401,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"LEO UNUS SED LEO","currencyPriceId":499376,"currencyId":421,"circulatingVolume":999498893},{"priceChange":-6.8,"symbol":"XMR","marketCap":1.757411199E9,"dexId":0,"rankMarketCap":14,"currentPrice":102.98,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":283773455,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"XMR Monero","currencyPriceId":499377,"currencyId":420,"circulatingVolume":17065120},{"priceChange":-3.7,"symbol":"DASH","marketCap":1.529093003E9,"dexId":0,"rankMarketCap":15,"currentPrice":172.08,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":565029080,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"DASH Dash","currencyPriceId":499378,"currencyId":1,"circulatingVolume":8885994},{"priceChange":-5.7,"symbol":"NEO","marketCap":1.357277971E9,"dexId":0,"rankMarketCap":16,"currentPrice":19.24,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":1155975824,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"NEO NEO","currencyPriceId":499379,"currencyId":424,"circulatingVolume":70538831},{"priceChange":-4,"symbol":"MIOTA","marketCap":1.230840012E9,"dexId":0,"rankMarketCap":17,"currentPrice":0.442823,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":53252154,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"MIOTA IOTA","currencyPriceId":499380,"currencyId":423,"circulatingVolume":2779530283},{"priceChange":-8.7,"symbol":"ATOM","marketCap":1.154095916E9,"dexId":0,"rankMarketCap":18,"currentPrice":6.05,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":119895968,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ATOM Cosmos","currencyPriceId":499381,"currencyId":422,"circulatingVolume":190688439},{"priceChange":-3.3,"symbol":"ETC","marketCap":1.000140631E9,"dexId":0,"rankMarketCap":19,"currentPrice":8.97,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":1261283511,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ETC Ethereum Classic","currencyPriceId":499382,"currencyId":425,"circulatingVolume":111513109},{"priceChange":4.7,"symbol":"XEM","marketCap":8.89759598E8,"dexId":0,"rankMarketCap":20,"currentPrice":0.098862,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":67508932,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"XEM NEM","currencyPriceId":499383,"currencyId":426,"circulatingVolume":8999999999},{"priceChange":15.3,"symbol":"LINK","marketCap":8.39219641E8,"dexId":0,"rankMarketCap":21,"currentPrice":2.4,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":185193477,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"LINK Chainlink","currencyPriceId":499384,"currencyId":431,"circulatingVolume":350000000},{"priceChange":-7.7,"symbol":"ONT","marketCap":8.15138349E8,"dexId":0,"rankMarketCap":22,"currentPrice":1.65,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":261506527,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ONT Ontology","currencyPriceId":499385,"currencyId":430,"circulatingVolume":494757215},{"priceChange":2.9,"symbol":"ZEC","marketCap":7.55801541E8,"dexId":0,"rankMarketCap":23,"currentPrice":110.46,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":931321995,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ZEC Zcash","currencyPriceId":499386,"currencyId":429,"circulatingVolume":6842344},{"priceChange":-4.4,"symbol":"MKR","marketCap":7.34334317E8,"dexId":0,"rankMarketCap":24,"currentPrice":734.33,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":2312133,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"MKR Maker","currencyPriceId":499387,"currencyId":427,"circulatingVolume":1000000},{"priceChange":-1.2,"symbol":"XTZ","marketCap":7.29694655E8,"dexId":0,"rankMarketCap":25,"currentPrice":1.11,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":12033890,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"XTZ Tezos","currencyPriceId":499388,"currencyId":428,"circulatingVolume":659324825},{"priceChange":-7.4,"symbol":"BTG","marketCap":5.12903938E8,"dexId":0,"rankMarketCap":26,"currentPrice":29.29,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":23378392,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BTG Bitcoin Gold","currencyPriceId":499389,"currencyId":432,"circulatingVolume":17513924},{"priceChange":-7.3,"symbol":"QTUM","marketCap":4.98411596E8,"dexId":0,"rankMarketCap":27,"currentPrice":5.2,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":848267758,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"QTUM Qtum","currencyPriceId":499390,"currencyId":437,"circulatingVolume":95824192},{"priceChange":-15.9,"symbol":"CRO","marketCap":4.61351314E8,"dexId":0,"rankMarketCap":28,"currentPrice":0.063167,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":5393975,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"CRO Crypto.com Chain","currencyPriceId":499391,"currencyId":433,"circulatingVolume":7303652968},{"priceChange":-14.1,"symbol":"VET","marketCap":4.49854871E8,"dexId":0,"rankMarketCap":29,"currentPrice":0.008112,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":130988530,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"VET VeChain","currencyPriceId":499392,"currencyId":436,"circulatingVolume":55454734800},{"priceChange":-2.6,"symbol":"DOGE","marketCap":3.91621957E8,"dexId":0,"rankMarketCap":30,"currentPrice":0.003261,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":62643524,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"DOGE Dogecoin","currencyPriceId":499393,"currencyId":435,"circulatingVolume":120094185233},{"priceChange":4.5,"symbol":"OMG","marketCap":3.88906177E8,"dexId":0,"rankMarketCap":31,"currentPrice":2.77,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":251270690,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"OMG OmiseGO","currencyPriceId":499394,"currencyId":440,"circulatingVolume":140245398},{"priceChange":-3.8,"symbol":"BAT","marketCap":3.82813206E8,"dexId":0,"rankMarketCap":32,"currentPrice":0.301154,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":65488171,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BAT Basic Attenti...","currencyPriceId":499395,"currencyId":434,"circulatingVolume":1271156300},{"priceChange":10,"symbol":"DCR","marketCap":3.57285245E8,"dexId":0,"rankMarketCap":33,"currentPrice":35.75,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":43694631,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"DCR Decred","currencyPriceId":499396,"currencyId":439,"circulatingVolume":9993853},{"priceChange":0.9,"symbol":"USDC","marketCap":3.55885904E8,"dexId":0,"rankMarketCap":34,"currentPrice":1.01,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":311156019,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"USDC USD Coin","currencyPriceId":499397,"currencyId":438,"circulatingVolume":351963568},{"priceChange":1,"symbol":"VSYS","marketCap":2.72330214E8,"dexId":0,"rankMarketCap":35,"currentPrice":0.156249,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":12684145,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"VSYS V Systems","currencyPriceId":499398,"currencyId":2052,"circulatingVolume":1742921658},{"priceChange":-7.7,"symbol":"BTT","marketCap":2.5526402E8,"dexId":0,"rankMarketCap":36,"currentPrice":0.001203,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":81837345,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BTT BitTorrent","currencyPriceId":499399,"currencyId":441,"circulatingVolume":212116500000},{"priceChange":0.2,"symbol":"BCD","marketCap":2.51707634E8,"dexId":0,"rankMarketCap":37,"currentPrice":1.35,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":15686471,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BCD Bitcoin Diamond","currencyPriceId":499400,"currencyId":443,"circulatingVolume":186492898},{"priceChange":-1.3,"symbol":"LSK","marketCap":2.41424577E8,"dexId":0,"rankMarketCap":38,"currentPrice":2.05,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":14884570,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"LSK Lisk","currencyPriceId":499401,"currencyId":442,"circulatingVolume":118043480},{"priceChange":-5.6,"symbol":"HOT","marketCap":2.33250085E8,"dexId":0,"rankMarketCap":39,"currentPrice":0.001751,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":36970529,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"HOT Holo","currencyPriceId":499402,"currencyId":444,"circulatingVolume":133214575156},{"priceChange":-4.7,"symbol":"RVN","marketCap":2.27163732E8,"dexId":0,"rankMarketCap":40,"currentPrice":0.059039,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":60347123,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"RVN Ravencoin","currencyPriceId":499403,"currencyId":446,"circulatingVolume":3847705000},{"priceChange":-12.1,"symbol":"HC","marketCap":2.21489828E8,"dexId":0,"rankMarketCap":41,"currentPrice":5.09,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":69307562,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"HC HyperCash","currencyPriceId":499404,"currencyId":375,"circulatingVolume":43529781},{"priceChange":0.9,"symbol":"TUSD","marketCap":2.14261633E8,"dexId":0,"rankMarketCap":42,"currentPrice":1.01,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":540811975,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"TUSD TrueUSD","currencyPriceId":499405,"currencyId":447,"circulatingVolume":212243411},{"priceChange":-1.1,"symbol":"NPXS","marketCap":2.12739592E8,"dexId":0,"rankMarketCap":43,"currentPrice":8.97E-4,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":18292078,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"NPXS Pundi X","currencyPriceId":499406,"currencyId":448,"circulatingVolume":237116087583},{"priceChange":-7.6,"symbol":"WAVES","marketCap":2.12350686E8,"dexId":0,"rankMarketCap":44,"currentPrice":2.12,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":36910475,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"WAVES Waves","currencyPriceId":499407,"currencyId":445,"circulatingVolume":100000000},{"priceChange":1.4,"symbol":"HEDG","marketCap":2.00830056E8,"dexId":0,"rankMarketCap":45,"currentPrice":0.696374,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":688639,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"HEDG HedgeTrade","currencyPriceId":499408,"currencyId":466,"circulatingVolume":288393814},{"priceChange":0,"symbol":"REP","marketCap":1.95610114E8,"dexId":0,"rankMarketCap":46,"currentPrice":17.78,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":12624487,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"REP Augur","currencyPriceId":499409,"currencyId":449,"circulatingVolume":11000000},{"priceChange":2.2,"symbol":"AOA","marketCap":1.94347777E8,"dexId":0,"rankMarketCap":47,"currentPrice":0.029706,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":4711891,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"AOA Aurora","currencyPriceId":499410,"currencyId":453,"circulatingVolume":6542330148},{"priceChange":3.1,"symbol":"HT","marketCap":1.93659682E8,"dexId":0,"rankMarketCap":48,"currentPrice":3.87,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":102708321,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"HT Huobi Token","currencyPriceId":499411,"currencyId":454,"circulatingVolume":50000200},{"priceChange":-4.7,"symbol":"ZRX","marketCap":1.90539616E8,"dexId":0,"rankMarketCap":49,"currentPrice":0.318751,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":29912240,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ZRX 0x","currencyPriceId":499412,"currencyId":450,"circulatingVolume":597769457},{"priceChange":-5.5,"symbol":"BTS","marketCap":1.90014398E8,"dexId":0,"rankMarketCap":50,"currentPrice":0.069602,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":16477843,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BTS BitShares","currencyPriceId":499413,"currencyId":457,"circulatingVolume":2730010000},{"priceChange":3.3,"symbol":"BCN","marketCap":1.88300136E8,"dexId":0,"rankMarketCap":51,"currentPrice":0.001023,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":218974,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BCN Bytecoin","currencyPriceId":499414,"currencyId":456,"circulatingVolume":184066828814},{"priceChange":-3.6,"symbol":"NANO","marketCap":1.85153443E8,"dexId":0,"rankMarketCap":52,"currentPrice":1.39,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":14054685,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"NANO Nano","currencyPriceId":499415,"currencyId":451,"circulatingVolume":133248297},{"priceChange":-7,"symbol":"BTM","marketCap":1.84971859E8,"dexId":0,"rankMarketCap":53,"currentPrice":0.184511,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":63016680,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"BTM Bytom","currencyPriceId":499416,"currencyId":452,"circulatingVolume":1002499275},{"priceChange":5,"symbol":"QBIT","marketCap":1.84525596E8,"dexId":0,"rankMarketCap":54,"currentPrice":65.7,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":78829,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"QBIT Qubitica","currencyPriceId":499417,"currencyId":460,"circulatingVolume":2808650},{"priceChange":-6.1,"symbol":"MONA","marketCap":1.78216767E8,"dexId":0,"rankMarketCap":55,"currentPrice":2.71,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":24227202,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"MONA MonaCoin","currencyPriceId":499418,"currencyId":455,"circulatingVolume":65729675},{"priceChange":7.8,"symbol":"THR","marketCap":1.72499662E8,"dexId":0,"rankMarketCap":56,"currentPrice":1989.94,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":205016,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"THR ThoreCoin","currencyPriceId":499419,"currencyId":467,"circulatingVolume":86686},{"priceChange":1.8,"symbol":"ICX","marketCap":1.63048254E8,"dexId":0,"rankMarketCap":57,"currentPrice":0.344415,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":35856676,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ICX ICON","currencyPriceId":499420,"currencyId":463,"circulatingVolume":473406688},{"priceChange":8.2,"symbol":"ABBC","marketCap":1.61527081E8,"dexId":0,"rankMarketCap":58,"currentPrice":0.319805,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":75186755,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ABBC ABBC Coin","currencyPriceId":499421,"currencyId":482,"circulatingVolume":505080602},{"priceChange":-10.5,"symbol":"DGB","marketCap":1.58199994E8,"dexId":0,"rankMarketCap":59,"currentPrice":0.013227,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":3931851,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"DGB DigiByte","currencyPriceId":499422,"currencyId":461,"circulatingVolume":11960340202},{"priceChange":11.3,"symbol":"ETP","marketCap":1.58128861E8,"dexId":0,"rankMarketCap":60,"currentPrice":2.21,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":130009014,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ETP Metaverse ETP","currencyPriceId":499423,"currencyId":469,"circulatingVolume":71682723},{"priceChange":-11.5,"symbol":"ZIL","marketCap":1.57686991E8,"dexId":0,"rankMarketCap":61,"currentPrice":0.018151,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":46360787,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ZIL Zilliqa","currencyPriceId":499424,"currencyId":459,"circulatingVolume":8687360058},{"priceChange":-8.9,"symbol":"IOST","marketCap":1.57320198E8,"dexId":0,"rankMarketCap":62,"currentPrice":0.013095,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":58963147,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"IOST IOST","currencyPriceId":499425,"currencyId":468,"circulatingVolume":12013965609},{"priceChange":-3.5,"symbol":"EGT","marketCap":1.56658121E8,"dexId":0,"rankMarketCap":63,"currentPrice":0.037278,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":42255038,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"EGT Egretia","currencyPriceId":499426,"currencyId":481,"circulatingVolume":4202394445},{"priceChange":-2.6,"symbol":"KCS","marketCap":1.46915985E8,"dexId":0,"rankMarketCap":64,"currentPrice":1.64,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":36186353,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"KCS KuCoin Shares","currencyPriceId":499427,"currencyId":462,"circulatingVolume":89659415},{"priceChange":-13.3,"symbol":"DENT","marketCap":1.46687966E8,"dexId":0,"rankMarketCap":65,"currentPrice":0.002028,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":7080617,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"DENT Dent","currencyPriceId":499428,"currencyId":458,"circulatingVolume":72345838994},{"priceChange":8.1,"symbol":"INB","marketCap":1.44339507E8,"dexId":0,"rankMarketCap":66,"currentPrice":0.412513,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":2701,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"INB Insight Chain","currencyPriceId":499429,"currencyId":484,"circulatingVolume":349902689},{"priceChange":-6.5,"symbol":"AE","marketCap":1.42968195E8,"dexId":0,"rankMarketCap":67,"currentPrice":0.535515,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":37818660,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"AE Aeternity","currencyPriceId":499430,"currencyId":470,"circulatingVolume":266973449},{"priceChange":0.9,"symbol":"PAX","marketCap":1.41875846E8,"dexId":0,"rankMarketCap":68,"currentPrice":1.01,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":263215422,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"PAX Paxos Standar...","currencyPriceId":499431,"currencyId":464,"circulatingVolume":140528247},{"priceChange":-7.9,"symbol":"KMD","marketCap":1.41857555E8,"dexId":0,"rankMarketCap":69,"currentPrice":1.24,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":8117837,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"KMD Komodo","currencyPriceId":499432,"currencyId":465,"circulatingVolume":114643207},{"priceChange":-1.7,"symbol":"SC","marketCap":1.37150438E8,"dexId":0,"rankMarketCap":70,"currentPrice":0.003325,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":6076133,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"SC Siacoin","currencyPriceId":499433,"currencyId":473,"circulatingVolume":41245562160},{"priceChange":-8.6,"symbol":"XVG","marketCap":1.36781037E8,"dexId":0,"rankMarketCap":71,"currentPrice":0.008663,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":8357205,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"XVG Verge","currencyPriceId":499434,"currencyId":471,"circulatingVolume":15788956759},{"priceChange":11.7,"symbol":"XIN","marketCap":1.34957681E8,"dexId":0,"rankMarketCap":72,"currentPrice":298.82,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":584263,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"XIN Mixin","currencyPriceId":499435,"currencyId":480,"circulatingVolume":451635},{"priceChange":-8.4,"symbol":"GXC","marketCap":1.29334048E8,"dexId":0,"rankMarketCap":73,"currentPrice":2.16,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":14438504,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"GXC GXChain","currencyPriceId":499436,"currencyId":472,"circulatingVolume":60000000},{"priceChange":-3.6,"symbol":"STEEM","marketCap":1.29231295E8,"dexId":0,"rankMarketCap":74,"currentPrice":0.406301,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":4440918,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"STEEM Steem","currencyPriceId":499437,"currencyId":474,"circulatingVolume":318068183},{"priceChange":5.9,"symbol":"VEST","marketCap":1.23441685E8,"dexId":0,"rankMarketCap":75,"currentPrice":0.017439,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":345847,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"VEST VestChain","currencyPriceId":499438,"currencyId":477,"circulatingVolume":7078400000},{"priceChange":-20,"symbol":"NRG","marketCap":1.202175E8,"dexId":0,"rankMarketCap":76,"currentPrice":6.84,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":1409902,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"NRG Energi","currencyPriceId":499439,"currencyId":23,"circulatingVolume":17568146},{"priceChange":-4.7,"symbol":"ARDR","marketCap":1.20127227E8,"dexId":0,"rankMarketCap":77,"currentPrice":0.120248,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":3805537,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ARDR Ardor","currencyPriceId":499440,"currencyId":475,"circulatingVolume":998999495},{"priceChange":-8,"symbol":"THETA","marketCap":1.09303953E8,"dexId":0,"rankMarketCap":78,"currentPrice":0.125564,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":11549354,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"THETA THETA","currencyPriceId":499441,"currencyId":476,"circulatingVolume":870502690},{"priceChange":-6.4,"symbol":"SNT","marketCap":1.07007828E8,"dexId":0,"rankMarketCap":79,"currentPrice":0.030834,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":35442459,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"SNT Status","currencyPriceId":499442,"currencyId":485,"circulatingVolume":3470483788},{"priceChange":-13.3,"symbol":"ELF","marketCap":1.05051154E8,"dexId":0,"rankMarketCap":80,"currentPrice":0.211784,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":37215845,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ELF aelf","currencyPriceId":499443,"currencyId":486,"circulatingVolume":496030000},{"priceChange":-13.4,"symbol":"MCO","marketCap":1.02528737E8,"dexId":0,"rankMarketCap":81,"currentPrice":6.49,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":24010964,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"MCO Crypto.com","currencyPriceId":499444,"currencyId":488,"circulatingVolume":15793831},{"priceChange":-6.8,"symbol":"NEX","marketCap":9.9411076E7,"dexId":0,"rankMarketCap":82,"currentPrice":2.75,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":2561949,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"NEX Nash Exchange","currencyPriceId":499445,"currencyId":478,"circulatingVolume":36196678},{"priceChange":-8,"symbol":"LAMB","marketCap":9.762414E7,"dexId":0,"rankMarketCap":83,"currentPrice":0.195248,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":44930401,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"LAMB Lambda","currencyPriceId":499446,"currencyId":514,"circulatingVolume":500000000},{"priceChange":-7.1,"symbol":"ENJ","marketCap":9.6703074E7,"dexId":0,"rankMarketCap":84,"currentPrice":0.125315,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":12125292,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ENJ Enjin Coin","currencyPriceId":499447,"currencyId":479,"circulatingVolume":771679781},{"priceChange":-1.4,"symbol":"STRAT","marketCap":9.4915634E7,"dexId":0,"rankMarketCap":85,"currentPrice":0.955049,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":7050915,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"STRAT Stratis","currencyPriceId":499448,"currencyId":490,"circulatingVolume":99383049},{"priceChange":-4.5,"symbol":"XZC","marketCap":9.4656975E7,"dexId":0,"rankMarketCap":86,"currentPrice":12.14,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":4693907,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"XZC Zcoin","currencyPriceId":499449,"currencyId":5,"circulatingVolume":7795743},{"priceChange":-2,"symbol":"MAID","marketCap":9.3969649E7,"dexId":0,"rankMarketCap":87,"currentPrice":0.207644,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":719576,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"MAID MaidSafeCoin","currencyPriceId":499450,"currencyId":491,"circulatingVolume":452552412},{"priceChange":-3.1,"symbol":"GNT","marketCap":9.3652865E7,"dexId":0,"rankMarketCap":88,"currentPrice":0.097105,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":4358066,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"GNT Golem","currencyPriceId":499451,"currencyId":487,"circulatingVolume":964450000},{"priceChange":-1.8,"symbol":"DAI","marketCap":8.6135146E7,"dexId":0,"rankMarketCap":89,"currentPrice":0.994814,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":27974106,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"DAI Dai","currencyPriceId":499452,"currencyId":492,"circulatingVolume":86584138},{"priceChange":-7.9,"symbol":"SOLVE","marketCap":8.5479869E7,"dexId":0,"rankMarketCap":90,"currentPrice":0.262915,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":8789935,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"SOLVE SOLVE","currencyPriceId":499453,"currencyId":483,"circulatingVolume":325123144},{"priceChange":-9.9,"symbol":"EKT","marketCap":8.4089824E7,"dexId":0,"rankMarketCap":91,"currentPrice":0.119123,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":4248584,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"EKT EDUCare","currencyPriceId":499454,"currencyId":500,"circulatingVolume":705908893},{"priceChange":-9.3,"symbol":"WAX","marketCap":8.2250191E7,"dexId":0,"rankMarketCap":92,"currentPrice":0.087238,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":2915388,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"WAX WAX","currencyPriceId":499455,"currencyId":489,"circulatingVolume":942821662},{"priceChange":-12.8,"symbol":"NAS","marketCap":7.5766012E7,"dexId":0,"rankMarketCap":93,"currentPrice":1.56,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":14035049,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"NAS Nebulas","currencyPriceId":499456,"currencyId":497,"circulatingVolume":48627715},{"priceChange":16,"symbol":"CCCX","marketCap":7.2941113E7,"dexId":0,"rankMarketCap":94,"currentPrice":0.019319,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":92645,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"CCCX Clipper Coin","currencyPriceId":499457,"currencyId":505,"circulatingVolume":3775570996},{"priceChange":-4.9,"symbol":"ELA","marketCap":7.2296015E7,"dexId":0,"rankMarketCap":95,"currentPrice":4.63,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":25546460,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"ELA Elastos","currencyPriceId":499458,"currencyId":513,"circulatingVolume":15612995},{"priceChange":-0.4,"symbol":"SAN","marketCap":7.2154834E7,"dexId":0,"rankMarketCap":96,"currentPrice":1.15,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":109105,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"SAN Santiment Net...","currencyPriceId":499459,"currencyId":494,"circulatingVolume":62660371},{"priceChange":-8.4,"symbol":"GRIN","marketCap":7.1680977E7,"dexId":0,"rankMarketCap":97,"currentPrice":5.13,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":52470836,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"GRIN Grin","currencyPriceId":499460,"currencyId":501,"circulatingVolume":13977000},{"priceChange":-10.1,"symbol":"PAI","marketCap":7.1335745E7,"dexId":0,"rankMarketCap":98,"currentPrice":0.049062,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":8466363,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"PAI Project Pai","currencyPriceId":499461,"currencyId":498,"circulatingVolume":1453985465},{"priceChange":-0.5,"symbol":"MXM","marketCap":7.080577E7,"dexId":0,"rankMarketCap":99,"currentPrice":0.042939,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":4490547,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"MXM Maximine Coin","currencyPriceId":499462,"currencyId":496,"circulatingVolume":1649000000},{"priceChange":-9.8,"symbol":"TRUE","marketCap":6.6841053E7,"dexId":0,"rankMarketCap":100,"currentPrice":0.83997,"updateTime":1561604113040,"dexName":"dexname","batchTime":1561604113040,"volume":61263857,"createTime":1561604113040,"infoUrl":"https://coinmarketcap.com","name":"TRUE TrueChain","currencyPriceId":499463,"currencyId":493,"circulatingVolume":79575543}]
     * actionUrl : currencyPricePartPage?1=1
     * pageSize : 100
     * sortBy :
     * sort : 0
     * batchTime : 2019-06-27 10:55:13
     */

    private int total;
    private int offset;
    private int pageTotal;
    private int pageNo;
    private String actionUrl;
    private int pageSize;
    private String sortBy;
    private int sort;
    private String batchTime;
    private List<DataListBean> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * priceChange : 7.3
         * symbol : BTC
         * marketCap : 2.28734228826E11
         * dexId : 0
         * rankMarketCap : 1
         * currentPrice : 12862.69
         * updateTime : 1561604113040
         * dexName : dexname
         * batchTime : 1561604113040
         * volume : 46365352774
         * createTime : 1561604113040
         * infoUrl : https://coinmarketcap.com
         * name : BTC Bitcoin
         * currencyPriceId : 499364
         * currencyId : 408
         * circulatingVolume : 17782762
         */

        private double priceChange;
        private String symbol;
        private double marketCap;
        private int dexId;
        private int rankMarketCap;
        private double currentPrice;
        private long updateTime;
        private String dexName;
        private long batchTime;
        private long volume;
        private long createTime;
        private String infoUrl;
        private String name;
        private int currencyPriceId;
        private int currencyId;
        private long circulatingVolume;

        public double getPriceChange() {
            return priceChange;
        }

        public void setPriceChange(double priceChange) {
            this.priceChange = priceChange;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getMarketCap() {
            return marketCap;
        }

        public void setMarketCap(double marketCap) {
            this.marketCap = marketCap;
        }

        public int getDexId() {
            return dexId;
        }

        public void setDexId(int dexId) {
            this.dexId = dexId;
        }

        public int getRankMarketCap() {
            return rankMarketCap;
        }

        public void setRankMarketCap(int rankMarketCap) {
            this.rankMarketCap = rankMarketCap;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getDexName() {
            return dexName;
        }

        public void setDexName(String dexName) {
            this.dexName = dexName;
        }

        public long getBatchTime() {
            return batchTime;
        }

        public void setBatchTime(long batchTime) {
            this.batchTime = batchTime;
        }

        public long getVolume() {
            return volume;
        }

        public void setVolume(long volume) {
            this.volume = volume;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getInfoUrl() {
            return infoUrl;
        }

        public void setInfoUrl(String infoUrl) {
            this.infoUrl = infoUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCurrencyPriceId() {
            return currencyPriceId;
        }

        public void setCurrencyPriceId(int currencyPriceId) {
            this.currencyPriceId = currencyPriceId;
        }

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
        }

        public long getCirculatingVolume() {
            return circulatingVolume;
        }

        public void setCirculatingVolume(long circulatingVolume) {
            this.circulatingVolume = circulatingVolume;
        }
    }
}
