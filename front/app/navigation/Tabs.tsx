import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import History from "../screens/History";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { mainBlue } from "../color";
import { useEffect, useState } from "react";
import PayStack from "./PayStack";
import MypageStack from "./MypageStack";
import Benefit from "../screens/Benefit";
import HomeStack from "./HomeStack";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";
import { Alert } from "react-native";

interface PropsType {
  // 로그인 여부 set
  setLogin: (a: any) => void;
}

const Tab = createBottomTabNavigator();

const Tabs: React.FC<PropsType> = ({ setLogin }) => {
  // const
  // Axios 새로운 인증 토큰 url
  const tokenUrl = "http://k6S101.p.ssafy.io:8080/user/token";
  // Axios 내 정보 조회 url
  const getMyInfoUrl = "http://k6S101.p.ssafy.io:8080/pay";
  // Axios 2차 인증 정보 등록 여부 확인 url
  const getMyAuthUrl = "http://k6S101.p.ssafy.io:8080/pay/auth";

  // useState
  // 잔액
  const [balance, setBalance] = useState<string>("0");
  // 아이디
  const [id, setId] = useState<string>("Temp ID");
  // 계좌 연결 정보
  const [bankInfo, setBankInfo] = useState({
    // 은행 이미지
    bankImg:
      "http://www.shinhangroup.com/kr/asset/images/introduce/ci_story_04.jpg",
    // 은행 이름
    bankName: null,
    // 계좌 번호
    bankNum: null,
  });
  // 2차 인증 정보 등록 여부
  const [auth, setAuth] = useState({
    otp: false,
    bio: false,
  });

  // Axios
  // 새로운 인증 토큰 발급
  const getNewToken = async (): Promise<any> => {
    const refreshToken = await AsyncStorage.getItem("refreshToken");
    await axios({
      method: "get",
      url: tokenUrl,
      headers: { Authorization: `Bearer ${refreshToken}` },
      params: refreshToken,
    })
      .then((response) => {
        console.log(response.data);
        AsyncStorage.setItem("accessToken", response.data.accessToken);
        return true;
      })
      .catch((err) => {
        console.log(err);
        Alert.alert("토큰 만료! 다시 로그인해주세요.");
        AsyncStorage.clear();
        setLogin(false);
        return false;
      });
  };
  // 내 정보 조회
  const getMyInfo = async () => {
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "get",
      url: getMyInfoUrl,
      headers: { Authorization: `Bearer ${accessToken}` },
    })
      .then((res) => {
        console.log(res.data);
        setBalance(res.data.balance);
        setId(res.data.id);
        setBankInfo(res.data.bankInfo);
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken?.())) getMyInfo();
      });
  };
  // 2차 인증 정보 등록 여부 확인
  const getMyAuth = async () => {
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "get",
      url: getMyAuthUrl,
      headers: { Authorization: `Bearer ${accessToken}` },
    })
      .then((res) => {
        console.log(res.data);
        setAuth(res.data);
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken?.())) getMyAuth();
      });
  };

  // useEffect
  useEffect(() => {
    getMyInfo();
    getMyAuth();
  }, []);

  return (
    <Tab.Navigator
      initialRouteName="Home"
      screenOptions={{
        tabBarLabelStyle: { fontWeight: "bold", fontSize: 13 },
        tabBarActiveTintColor: mainBlue,
        tabBarInactiveTintColor: "black",
        headerShown: false,
        tabBarHideOnKeyboard: true,
      }}
      sceneContainerStyle={{
        backgroundColor: "white",
      }}
    >
      {/* 거래 내역 */}
      <Tab.Screen
        name="History"
        options={{
          title: "거래 내역",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="newspaper-variant-multiple-outline"
                size={size}
                color={color}
              />
            );
          },
        }}
      >
        {(props) => (
          <History
            {...props}
            balance={balance}
            setBalance={setBalance}
            getNewToken={getNewToken}
            getMyInfo={getMyInfo}
          />
        )}
      </Tab.Screen>
      {/* 페이 */}
      <Tab.Screen
        name="Pay"
        options={{
          title: "페이",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="cash-plus"
                size={size}
                color={color}
              />
            );
          },
        }}
      >
        {(props) => (
          <PayStack
            {...props}
            balance={balance}
            bankInfo={bankInfo}
            setBalance={setBalance}
            getNewToken={getNewToken}
            auth={auth}
          />
        )}
      </Tab.Screen>
      {/* 홈 */}
      <Tab.Screen
        name="Home"
        options={{
          title: "홈",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="home-outline"
                size={size}
                color={color}
              />
            );
          },
        }}
      >
        {(props) => (
          <HomeStack
            {...props}
            balance={balance}
            id={id}
            setBalance={setBalance}
            getNewToken={getNewToken}
            auth={auth}
          />
        )}
      </Tab.Screen>
      {/* 혜택 */}
      <Tab.Screen
        name="Benefit"
        component={Benefit}
        options={{
          title: "혜택",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="lightning-bolt-outline"
                size={size}
                color={color}
              />
            );
          },
        }}
      />
      {/* 내 정보 */}
      <Tab.Screen
        name="Mypage"
        options={{
          title: "내 정보",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="account-outline"
                size={size}
                color={color}
              />
            );
          },
        }}
      >
        {(props) => (
          <MypageStack
            {...props}
            setLogin={setLogin}
            bankInfo={bankInfo}
            setBankInfo={setBankInfo}
            getNewToken={getNewToken}
            auth={auth}
            getMyAuth={getMyAuth}
            getMyInfo={getMyInfo}
          />
        )}
      </Tab.Screen>
    </Tab.Navigator>
  );
};

export default Tabs;
