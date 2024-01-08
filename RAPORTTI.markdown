# Raportit tehtävistä

Kirjaa tähän tiedostoon **jokaiseen** tehtävään liittyvät omat raporttisi ja analyysisi. Muista että raportti on myös kurssilla **arvosteltava tehtävä**.

Voit sisällyttää raporttiin tekstimuotoisia taulukoita (tasaukset välilyönnein):

```
n     Fill     Search   Total
500   7        700      707
1000  9        288      297
```

Ja näihin liittyviä kuvatiedostoja:

![Esimerkkikuva](report-sample-image.png)

Nämä näkyvät sitten VS Coden Preview -näkymässä (tai oman repositorysi webbisivulla) oikein muotoiltuna. Käytä tässä dokumentissa olevia muotoiluja esimerkkinä kun kirjoitat raporttiasi. 

Huomaa että jos laitat kuvatiedostot vaikka omaan alihakemistoonsa, Markdown -muotoilussa on oltava suhteellinen polku tiedostoon, esimerkiksi `images/report-sample-image.png`. **Älä** käytä absoluuttisia polkuja `C:\Users\tippaleipa\kurssit\TIRA\kuva.png`, koska nämä eivät tietenkään toimi opettajan koneella. Ei kannata laittaa linkkiä etärepoosikaan, vaan nimenomaan paikalliseen tiedostoon.

Voit myös sisällyttää *lyhyitä* koodinpätkiä vaikkapa Java -formaatilla:

```Java
	@Override
	public int hashCode() {
		// Oma nerokas hajautufunktioni!
	}
```
Tarvittaessa käytä myös paremmin muotoiltuja taulukoita:

| n	| Fill	| Search	| Total |
|-----|--------|--------|-------|
| 500	 | 7	| 700	| 707 |
| 1000 |	9	| 288	| 297 | 

Alaluvut jokaisen tehtävän raportille löydät alta.


## 01-TASK

Suurin osa ensimmäisen tehtävän aihealueista olivat minulle jo ennestään tuttuja, joten en oppinut mitään täysin uutta. Tehtävä toimi hyvänä kertauksena aikaisemmin oppimiini asioihin.

Koska ensimmäiseen tehtävään tarvittavat algoritmit olivat melko yksinkertaisia, niiden oppiminen ei tuntunut vaikealta. 


Toteuttamani lajittelualgoritmin aikakompleksisuusluokka on O(N2), koska algoritmissa on kaksi sisäkkäistä silmukkaa.

Reverse -algoritmin aikakompleksisuusluokka taas on lineaarinen, eli O(N). Siksi taulukon järjestys on järkevämpi kääntää laskevaan järjestykseen kuin lajitella se uudelleen päinvastaiseen järjestykseen. 

## 02-TASK

Get-metodin aikakompleksisuusluokka on O(N), koska se käy kaikki elementit kerran läpi. Parhaimmassa tapauksessa aikakompleksisuudeksi tulee O(1), jos etsittävä elementti on indeksissä nolla.

Tehtävässä toteutettujen hakualgoritmien aikakompleksisuus on O(N). Ne käyvät aineiston läpi yhden kerran ja silloin suoritusaika kasvaa lineaarisesti aineiston koon kasvaessa. Samasta syystä tällaisia hakualgoritmeja kutsutaan lineaarisiksi.

Koodia tutkimalla päättelin, että SimpleContainerin add-algoritmin aikakompleksisuus on O(n), koska taulukko käydään normaalitilanteessa läpi kerran. Vaikka reallokointi tapahtuisi säilyisi aikakompleksisuusluokka O(n):nä, koska reallocate-metodi käy taulukon läpi kerran ja on ensimmäisen silmukan ulkopuolella. Mittaukset kuitenkin näyttäisivät, että tosiasiassa täyttöaika ei kasva lineaarisesti. En löytänyt syytä tälle.

![Task2 graafit](Tira_task2_pic.png)

Opin käyttämään Comparator -rajapintaa aikaisempaa paremmin ja tutustuin tehtävässä käytettyjen rajapintojen ja luokkien dokumentaatioon. Erot johtuvat siitä, missä järjestyksessä koodarit ovat, kun lajittelua aletaan tekemään: lisäyslajittelu on nopeampi, jos koodarit ovat jo osittain oikeassa järjestyksessä. Koodareiden ollessa päinvastaisessa järjestyksessä lajittelu on hitaampaa, silloin jokainen koodari on väärässä paikassa ja jokainen koodari täytyy siirtää oikealle paikalleen. Siksi tällaisessa tilanteessa on järkevämpi käyttää reverse-algoritmia.

## 03-TASK

Normaali haku on nopeampi kun haettava koodari on listan alkupäässä. Tämä johtuu sitä, että se käy koodarit läpi järjestyksessä: tällöin nopeimmin löytyy ensimmäinen koodari ja hitaimmin viimeinen koodari. Mitä suurempi koodaritiedosto oli valittuna sitä suurempia eroja hakutapojen välillä oli. Tämä johtuu siitä, että lineaarisen haun aikakompleksisuusluokka on O(n) ja puolitushaun aikakompleksisuusluokka on O(log n). 

Täyttöajassa ei ole huomattavaa eroa, koska se toimii edelleen samalla tavalla. Huomattavin ero löytyy haun suoritusajoista. Haun suoritusaikojen erot johtuvat käytettyjen hakualgoritmien aikakompleksisuusluokkien eroista: lineaarisessa haussa se on O(n) ja puolitushaussa O(log n). Puolitushaku ei kuitenkaan ole mahdollista, jos aineistoa ei ole järjestelty. Tarkastelemalla kokonaissuoritusaikaa huomaakin, että siinä ei ole huomattavia eroja. Puolitushakua kannattaa käyttää, jos aineisto on lajiteltu tai se kannattaa lajitella.

Puolitushaun aikakompleksisuusluokka on O(log n) koska hakualue jaetaan kahtia, joka kerta kun silmukka toistetaan. Taulukosta tätä on vaikea nähdä, koska jokaisella testikerralla ensimmäinen haku oli todella hidas.

![Task3 graafit](tira_task3.png)

## 04-TASK

Push-metodissa ei itsessään ole silmukoita, joten sen aikakompleksisuusluokka on O(1), jos reallokointia ei tarvitse tehdä. Reallokoidessa metodi kutsuu reallocate-metodia, joka sisältää yhden for-silmukan ja sen aikakompleksisuusluokka on O(n). Tällöin aikakompleksisuusvaatimukset täyttyvät.  
Metodeissa capacity, pop, peek, size, isEmpty ja clear ei ole silmukoita ja ne eivät kutsu muita metodeja, joten niiden aikakompleksisuusluokka on vaatimusten mukainen O(1). 
Metodi ToString käy itemArray-taulukon läpi kerran for-silmukan avulla. Siten sen aikakompleksisuusluokka on O(n) ja se toteuttaa tehtävässä annetut vaatimukset. 
Lainausmerkkien ollessa väärin ei sulkujen tarkistaminen toimi enää oikein. Ylimääräiset lainausmerkit saavat aikaan sen, että lainaus "sulkeutuu" liian aikaisin ja lainauksen sisään tarkoitetut sulut otetaan mukaan tarkistukseen. Jos lainausta ei ole suljettu asianmukaisesti ja sulkeva lainausmerkki puuttuu seuraus on se, että tarkistettavaksi tarkoitettuja sulkuja jää tarkistuksen ulkopuolelle. Myös erityyppiset lainausmerkit, kuten suomenkielessä yleisimmin käytetyt kaarevat lainausmerkit, voivat sekoittaa tarkistuksen. Nyt lainausmerkeiksi luetaan vain suorat lainausmerkit. 
Pinotietorakenne oli mielestäni helposti ymmärrettävä ja sen toteutus oli suoraviivaista. Eniten vaikeuksia tehtävässä aiheutti ParenthesesChecker-luokka 

Täysin uutta tehtävässä minulle olivat String ja StringBuilderin suuret nopeuserot. 

## 05-TASK

Jonossa tarvittavat elementit sijaitsevat aina linkitetyn lisan alku- tai loppupäässä, joten sen heittapuolet eivät tule tässä tapauksessa esille. Taulukkopohjainen ratkaisu voi olla hitaampi, etenkin jos uudelleenallokointia joudutaan tekemään usein. Täten, jos jonon pituutta ei tiedetä etukäteen voi linkitetyllä listalla toteutettu jonotietorakenne olla parempi kuin taulukkopohjainen ratkaisu.

Vaikeinta tehtävässä oli jonon ympäri pyörähtäminen ja jonon häntäpään uuden sijainnin laskeminen. Modulaarinen aritmetiikka ja jakojäännöksellä toteutettavat laskutoimitukset ovat minulle usein hieman vaikeita käsittää. 

Enqueue-metodin aikakompleksisuus on yleensä O(1), mutta reallokoinnin toteuttavaan if-haaraan mentäessä aikakompleksisuudeksi tulee O(n), koska se sisältää yhden for-silmukan.
Metodit dequeue ja element eivät sisällä silmukoita ja kutsuvat vain metodia isEmpty, jonka aikakompleksisuusluokka on O(1), joten myös niiden aikakompleksisuus on O(1). Metodit clear ja size eivät myöskään sisällä silmukoita ja ne eivät kutsu muita metodeja, siten niidenkin aikakompleksisuusluokka on O(1). Metodissa toString taulukon elementit käydään kerran läpi while-silmukan avulla, joten sen aikakompleksisuus luokka on O(n). Kaikki metodit siis toteuttavat vaatimukset niiden aikakompleksisuudesta.

## 06-TASK

Toteutin tehtävässä hyvin yksinkertaisen pikalajittelun. Koska se käyttää Lomuto partition-skeemaa sen aikatehokkuus on huonoimmassa tapauksessa O(n²), koska sarana-arvo otetaan aina taulukon loppupäästä. Testeissä tämä ei kuitenkaan tullut esille vaan lajittelu oli yleensä nopeampaa mitä suurempi testiaineisto oli. Testissä, jossa aineiston koko oli tuhat, keskimääräinen suoritusaika elementtiä kohden oli 0,036 millisekuntia. Testissä, jonka aineiston koko oli satatuhatta keskimääräinen suoritusaika oli 0,006 millisekuntia per elementti. 

![Task6 graafit](fastsort_tira.png)

Hitaan lajittelualgoritmin lisäyslajittelun aikatehokkuusluokka on O(n2). Tämän voi nähdä myös graafista. Hidasta lajittelualgoritmia käyttäessä tuhannen elementin lajittelussa yhtä elementtiä kohden meni 0,171 millisekuntia ja sadantuhannen elementin lajittelussa yhtä elementtiä kohden meni keskimäärin 9,345 millisekuntia. 

## 07-TASK

![Task7 graafit](BST_tira.png)

Toteutin binäärihakupuun metodit tavalla B. Jälkikäteen ajateltuna toteuttaisin metodit visitor-mallilla, koska mielestäni se olisi hyvä oppia. 

BST:hen lisäämisen aikatehokkuus pysyi hyvänä vaikka aineistot kasvoivat. Aineiston koon kasvaessa sata kertaiseksi lisäämiseen kulunut aika kasvoi vain 60 %. Myös hakemiseen kulunut aika väheni aineiston kasvaessa ja sen search time/item -sarakkeen arvot pienenivät, kun aineiston koko kasvoi.  Puut eivät siis olleet epämuodostuneita ja aikakompleksisuus oli lähempänä O(log n), kuin pahinta mahdollista skenaariota O(n). 

Indeksin perusteella hakeminen hidastui merkittävästi aineiston koon kasvaessa. 

## 08-TASK

Hajautustaulun add-metodin aikakompleksisuus voi olla huonoimmassa tapauksessa O(n), jos törmäyksiä sattuu paljon. Mittaustulosten mukaan vaikuttaa kuitenkin, että add-metodi toimii melko hyvin. Taulukosta näemme, että lisäysaika elementtiä kohden on pysytellyt lähes samana huolimatta aineistoin koosta. Ainoastaan pienimmässä aineistossa se on paljon suurempi. Oletan tämän johtuvan siitä, että pienemmällä aineistolla myös tyhjien paikkojen määrä on taulukossa pienempi ja törmäyksiä sattuu siksi enemmän. Yleisestiottaen add-metodin aikakompleksisuus on siis ollu O(1). Myös hakuaikojen aikatehokkuudessa on ollu vain pieniä eroja erikokoisilla aineistoilla. Hakeminen on nopeutunut hieman aineiston kasvaessa, mikä johtuu samasta siitä, että taulukon kasvaessa luotausta täytyy tehdä vähemmän.

![Task8 graafit](Hashtable_tira.png)

BST:hen verrattuna lisäysaika oli suunnilleen sama. Hakuajassa Hashtable oli nopeampi etenkin suurilla aineistoilla. 

## 09-TASK

Toteutin tehtävässä perusmetodien lisäksi syklien tunnistuksen sekä suunnatauista että suuntaamattomista verkoista, irtonaisten alueiden tunnistuksen, leveyshaun ja syvyyshaun.

Muunnettuani toteutusta verkon täyttönopeus pieneni huomattavasi. Esimerksi 10000 elementtiä sisältävällä aineistolla täyttöaika tippui 42931 millisekunnista 101 millisekuntiin. 
 
Mielestäni verkkotietorakenne oli helposti ymmärrettävä ja sen toteutus sujui hyvin. Ainoa ongelma oli DPS:n toteutus. Olin aluksi epähuomiossa tarkastanut onko nextNode:ssa jo vierailtu visited ArrayLististä enkä found HashSetistä. Tämän takia DPS oli äärimmäisen hidas suurilla aineistoilla.

Testatuissa graafeissa jokaista solmua kohden graafissa oli noin viisi reunaa. Koska aineistot olivat suuria olisi reunoja mahdollista olla paljon enemmänkin. Siksi testatut graafit olivat mielestäni harvoja. Koska TIRA-codersin koodareilla on jokaisella melko vähän ystäviä on reunuslista Map:illa toteutettuna parempi ratkaisu kuin matriisi. Matriisia tulisi käyttää, jos verkot olisivat tiheitä.

Hashtable oli nopeampi täyttöajassa mitattuna, mutta molemmat hakualgoritmit olivat hitaampia kuin HashMap:illa. Erot olivat todella pieniä pienillä aineistoilla, mutta suurimmilla aineistoilla ero hakuajoissa oli noin satatuhatta millisekuntia. Toisaalta ottaen huomioon kokonaisajan, joka hakuun merni ei noin puolentoista minuutin ero ole kovinkaan merkittävä.

Metodin createVertexFor aikakompleksisuus on O(1). Siinä ei ole silmukoita ja siinä käytetään HashMapin put:ia, jonka aikakompleksisuus on myös O(1). Myös metodin getVertices aikakompleksisuusluokka on O(1), koska siinä ei ole silmukoita ja key set on myös O(1) operaatio. Sama pätee myös muihin metodeihin lukuunottamatta BFS:sää ja DFS:ssää. Niiden aikakompleksisuus on solmujen ja reunojen yhteenlaskettu määrä eli O(n+m). Metodissa disconnectedVertices käytetään leveyshakua, joten myös sen aikakompleksisuusluokka on O(n+m). Metodi hasCycles on toteutettu samaan tapaan, kuin leveyshaku: se vierailee mahdollisesti kaikissa solmuissa ja niiden reunoissa, joten aikakompleksisuus on myös solmujen ja reunojen yhteenlaskettu määrä O(n+m).

![Task9 graafit](graph_tira.png)