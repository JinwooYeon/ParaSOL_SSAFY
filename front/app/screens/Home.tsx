import { Text } from "react-native";
import styled from "styled-components/native";

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

const Home = () => (
  <ContentContainer>
    <Text>Home</Text>
  </ContentContainer>
);

export default Home;
