//package com.rts.tap.service.impl.client;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.rts.tap.dao.client.ClientResetPasswordDAO;
//import com.rts.tap.dto.client.ClientDTO;
//import com.rts.tap.model.Client;
//
//import jakarta.persistence.EntityManager;
//
//class ClientResetPasswordServiceImplTest {
//	@Mock
//	private ClientResetPasswordDAO clientResetPasswordDAO;
//	@Mock
//	private EntityManager entityManager;
//	@InjectMocks
//	private ClientResetPasswordServiceImpl clientResetPasswordService;
//	private ClientDTO clientDTO;
//	private Client client;
//
//	@BeforeEach
//	public void setUp() {
//		clientDTO = new ClientDTO();
//		clientDTO.setClientId(1L);
//		clientDTO.setClientEmail("john.doe@example.com");
//		clientDTO.setPassword("newpassword");
//		client = new Client();
//		client.setClientId(1L);
//		client.setClientEmail("john.doe@example.com");
//		client.setPassword("oldpassword");
//	}
//
//	@Test
//	public void testResetPasswordByClientId_Success() {
//		when(entityManager.find(Client.class, anyLong())).thenReturn(client);
//		String result = clientResetPasswordService.resetPasswordByClientEmail(clientDTO);
//		assertEquals("Password reset success!", result);
//	}
//
//	@Test
//	public void testResetPasswordByClientId_ClientNotFound() {
//		when(entityManager.find(Client.class, anyLong())).thenReturn(null);
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			clientResetPasswordService.resetPasswordByClientEmail(clientDTO);
//		});
//		assertEquals("Client not found for ID: " + clientDTO.getClientId(), exception.getMessage());
//	}
//
//	@Test
//	public void testResetPasswordByClientId_SamePassword() {
//		when(entityManager.find(Client.class, anyLong())).thenReturn(client);
//		clientDTO.setPassword("oldpassword");
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			clientResetPasswordService.resetPasswordByClientEmail(clientDTO);
//		});
//		assertEquals("Old password and New password are same!", exception.getMessage());
//	}
//
//	@Test
//	public void testAddClient_Success() {
//		String result = clientResetPasswordService.addClient(clientDTO);
//		assertEquals("Client added successfully", result);
//		verify(clientResetPasswordDAO, times(1)).addClient(any(Client.class));
//	}
//
//	@Test
//	public void testAddClient_Failure() {
//		clientDTO = null;
//		String result = clientResetPasswordService.addClient(clientDTO);
//		assertEquals("Client add failure", result);
//		verify(clientResetPasswordDAO, never()).addClient(any(Client.class));
//	}
//
//}
