import { createNativeStackNavigator } from "@react-navigation/native-stack";
import BalanceBox from "../components/BalanceBox";
import BtnBox from "../components/BtnBox";
import Home from "../screens/Home";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
} from "../screens/styled";

interface PropsType {
  balance: string;
  navigation: any;
}

const Stack = createNativeStackNavigator();

const Transaction: React.FC<PropsType> = ({ balance, navigation }) => {
  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="파라솔 PAY" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer></ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="QR 스캔" navigation={navigation} />
          <BtnBox color="white" text="뒤로" navigation={navigation} />
          {/* <BtnBox color="red" text="초기화" navigation={navigation} /> */}
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

const HomeStack: React.FC<PropsType> = ({ balance }) => {
  return (
    <Stack.Navigator
      initialRouteName="HomeMain"
      screenOptions={{
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_right",
        headerShown: false,
      }}
    >
      <Stack.Screen name="MypageMain">
        {(props) => <Home {...props} balance={balance} />}
      </Stack.Screen>
      <Stack.Screen name="Transaction">
        {(props) => <Transaction {...props} balance={balance} />}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default HomeStack;
