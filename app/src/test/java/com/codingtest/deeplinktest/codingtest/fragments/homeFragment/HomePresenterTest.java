package com.codingtest.deeplinktest.codingtest.fragments.homeFragment;

import com.codingtest.deeplinktest.codingtest.apiService.model.Event;
import com.codingtest.deeplinktest.codingtest.apiService.repository.EventsStubRepository;
import com.codingtest.deeplinktest.codingtest.utility.CurrencyUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HomePresenterTest {
    @Mock
    private HomeFragmentMVP.RequiredViewOps fragment;

    private HomeFragmentMVP.PresenterOps presenter;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.presenter = new HomePresenter(fragment, new EventsStubRepository());
    }

    @Test
    public void testInit() throws Exception {
        ArgumentCaptor<Collection> argument = ArgumentCaptor.forClass(Collection.class);
        presenter.init();

        verify(fragment).showLoading();
        verify(fragment).showEvents((List<Event>) argument.capture());
        verify(fragment).dismissLoading();

        assertNotNull(argument.getValue());
        assertEquals(6, argument.getValue().size());
        assertEquals("Hamlet", ((List<Event>) argument.getValue()).get(2).name);
        assertEquals("The Nutcracker", ((List<Event>) argument.getValue()).get(3).name);
    }

    @Test
    public void testFloatingActionButtonOnClick() throws Exception {
        ArgumentCaptor<Collection> argument = ArgumentCaptor.forClass(Collection.class);

        presenter.init();
        verify(fragment).showEvents(anyList());

        presenter.floatingActionButtonOnClick();
        verify(fragment, times(2)).showEvents((List<Event>) argument.capture());

        assertNotNull(argument.getValue());
        assertEquals(2, argument.getValue().size());
        assertEquals("Othello", ((List<Event>) argument.getValue()).get(0).name);
        assertEquals("Hamlet", ((List<Event>) argument.getValue()).get(1).name);
    }

    @Test
    public void testOnSwipeRefresh() throws Exception {
        presenter.onSwipeRefresh();

        verify(fragment).showLoading();
        verify(fragment).showEvents(anyList());
        verify(fragment).dismissLoading();
    }

    /**
     * This test should go elsewhere, I'm leaving it here so it's a bit easier to read everything.
     * @throws Exception
     */
    @Test
    public void testCurrencyUtil() throws Exception {
        assertEquals("$1.00", CurrencyUtil.formatCurrency(new BigDecimal(1), 2,2));
        assertEquals("$10", CurrencyUtil.formatCurrency(new BigDecimal(10), 0,2));
        assertEquals("$100.9", CurrencyUtil.formatCurrency(new BigDecimal(100.98), 0,1));
        assertEquals("$100.93", CurrencyUtil.formatCurrency(new BigDecimal(100.93), 0,2));
    }
}