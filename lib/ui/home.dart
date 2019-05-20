import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  @override
  HomeState createState() => HomeState();
}

class HomeState extends State<Home> {

  final TextEditingController _weightController = TextEditingController();

  int radioValue = 0;
  double _finalResult = 0.0;
  int marsValue = 0;
  int plutoValue = 0;
  int venusValue = 0;

  void handleRadioValueChanged(int value) {
    setState(() {
      radioValue = value;

      switch(radioValue) {
        case 1:
          _finalResult = calculateWeight(_weightController.text, 0.06);
          plutoValue++;
          break;
        case 2:
          _finalResult = calculateWeight(_weightController.text, 0.38);
          marsValue++;
          break;
        case 3:
          venusValue++;
          _finalResult = calculateWeight(_weightController.text, 0.91);
      }

    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Weight on planet X"),
        centerTitle: true,
        backgroundColor: Colors.black38,
      ),
      backgroundColor: Colors.blueGrey,
      body: Container(
        alignment: Alignment.topCenter,
        child: ListView(
          padding: const EdgeInsets.all(2.5),
          children: <Widget>[
            Image.asset(
              'images/planet.png',
              height: 133.8,
              width: 200.0,
            ),
            Container(
              margin: const EdgeInsets.all(3.0),
              child: Column(
                children: <Widget>[
                  TextField(
                    controller: _weightController,
                    keyboardType: TextInputType.number,
                    decoration: new InputDecoration(
                        labelText: "Your weight on earth",
                        hintText: "In pounds",
                        icon: Icon(Icons.person_outline)),
                  ),
                  Padding(
                    padding: EdgeInsets.all(5.0),
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                      Radio<int>(
                          activeColor: Colors.brown,
                          value: 1,
                          groupValue: radioValue,
                          onChanged: handleRadioValueChanged),
                      Text(
                        "Pluto \n" + plutoValue.toString(),
                        style: TextStyle(color: Colors.white30),
                      ),
                      Radio<int>(
                          activeColor: Colors.redAccent,
                          value: 2,
                          groupValue: radioValue,
                          onChanged: handleRadioValueChanged),
                      Text(
                        "Mars \n" + marsValue.toString(),
                        style: TextStyle(color: Colors.white30),
                      ),
                      Radio<int>(
                          activeColor: Colors.orangeAccent,
                          value: 3,
                          groupValue: radioValue,
                          onChanged: handleRadioValueChanged),
                      Text(
                        "Venus \n" + venusValue.toString(),
                        style: TextStyle(color: Colors.white30),
                      ),
                    ],
                  ),
                  //RESULT TEXT
                  Padding(
                    padding: EdgeInsets.all(15.6),
                  ),
                  Text(
                    "$_finalResult lbs",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 19.4,
                      fontWeight: FontWeight.w400
                    ),
                  )
                ],
              ),
            )
          ],
        ),
      ),
    );
  }

  double calculateWeight(String weight, double multiplier) {
    if (int.parse(weight).toString().isNotEmpty && int.parse(weight) > 0 && int.parse(weight) < 1400) {
      return int.parse(weight) * multiplier;
    } else {
      print ("Enter a normal weight please...");
      return 0;
    }
  }
}
